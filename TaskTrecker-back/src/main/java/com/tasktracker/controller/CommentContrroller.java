package com.tasktracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasktracker.dto.CommentDTO;
import com.tasktracker.service.CommentService;

@RestController
@RequestMapping(value = "/api/comments")
public class CommentContrroller {

	@Autowired
	CommentService commentService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping()
	public ResponseEntity<List<CommentDTO>> getAll() {
		List<CommentDTO> retVal = commentService.findAll();
		if(retVal != null) {
			return new ResponseEntity<>(retVal, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<CommentDTO> getOne(@PathVariable Long id) {
		CommentDTO retVal = commentService.findOneById(id);
		if(retVal != null) {
			return new ResponseEntity<CommentDTO>(retVal, HttpStatus.OK);
		}
		return new ResponseEntity<CommentDTO>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping()
	public ResponseEntity<CommentDTO> create(@RequestBody CommentDTO commentDTO) {
		CommentDTO retVal = commentService.create(commentDTO);
		if(retVal != null) {
			return new ResponseEntity<CommentDTO>(retVal, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
		if(commentService.update(id, commentDTO)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if(commentService.delete(id)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
