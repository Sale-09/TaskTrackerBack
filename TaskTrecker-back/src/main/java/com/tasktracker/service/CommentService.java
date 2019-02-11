package com.tasktracker.service;

import java.util.List;

import com.tasktracker.dto.CommentDTO;

public interface CommentService {
	
	public List<CommentDTO> findAll();
	public CommentDTO findOneById(Long id);
	public CommentDTO create(CommentDTO commentDTO);
	public Boolean update(Long id, CommentDTO commentDTO);
	public Boolean delete(Long id);
}
