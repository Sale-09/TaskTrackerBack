package com.tasktracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tasktracker.dto.CommentDTO;
import com.tasktracker.dto.UserDTO;
import com.tasktracker.entity.Comment;
import com.tasktracker.repository.CommentRepository;
import com.tasktracker.repository.TaskRepository;
import com.tasktracker.repository.UserRepository;
import com.tasktracker.security.model.user.SecurityUser;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Override
	public List<CommentDTO> findAll() {
//		List<Comment> comments = commentRepository.findAll();
		List<Comment> comments = commentRepository.findAllByOrderByDateDesc();
		if(comments != null) {
			List<CommentDTO> commentsDTO = new ArrayList<>();
			for(Comment comment : comments) {
				CommentDTO commentDTO = new CommentDTO(comment);
				UserDTO userDTO = new UserDTO(comment.getUser());
				commentDTO.setUserDTO(userDTO);
				commentsDTO.add(commentDTO);
			}
			return commentsDTO;
		}
		return null;
	}

	@Override
	public CommentDTO findOneById(Long id) {
		Comment comment = commentRepository.findOne(id);
		if(comment != null) {
			CommentDTO commentDTO = new CommentDTO(comment);
			commentDTO.setUserDTO(new UserDTO(comment.getUser()));
			return commentDTO;
		}
		return null;
	}

	@Override
	public CommentDTO create(CommentDTO commentDTO) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		Comment comment = new Comment();
		comment.setDate(new Date());
		comment.setText(commentDTO.getText());
		SecurityUser user = userRepository.findByUsername(userName);
		comment.setUser(user);
		comment.setTask(taskRepository.findOne(commentDTO.getTaskId()));
		Comment newComment = commentRepository.save(comment);
		CommentDTO retVal = new CommentDTO(newComment);
		UserDTO userDTO = new UserDTO(user);
		retVal.setUserDTO(userDTO);
		return retVal;
	}

	@Override
	public Boolean update(Long id, CommentDTO commentDTO) {
		Comment comment = commentRepository.findOne(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		if(comment != null) {
			comment.setId(id);
			comment.setDate(new Date());
			comment.setText(commentDTO.getText());
			comment.setTask(taskRepository.findOne(commentDTO.getTaskId()));
			comment.setUser(userRepository.findByUsername(userName));
			commentRepository.save(comment);
			return true;
		}
		return false;
	}

	@Override
	public Boolean delete(Long id) {
		Comment comment = commentRepository.findOne(id);
		if(comment != null) {
			commentRepository.delete(comment);
			return true;
		}
		return false;
	}

}
