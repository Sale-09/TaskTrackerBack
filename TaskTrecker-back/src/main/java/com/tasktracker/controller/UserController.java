package com.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasktracker.dto.LoginDTO;
import com.tasktracker.dto.TokenDTO;
import com.tasktracker.security.configuration.TokenUtils;
import com.tasktracker.security.model.user.SecurityUser;
import com.tasktracker.service.UserDetailsServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@PostMapping(value = "/api/login")
	public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
		try {
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginDTO.getUsername());
			return new ResponseEntity<TokenDTO>(new TokenDTO(tokenUtils.generateToken(userDetails)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TokenDTO>(new TokenDTO("Invalid login"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/api/register")
	public ResponseEntity<TokenDTO> register(@RequestBody final SecurityUser user) {
		SecurityUser newUser = new SecurityUser();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser = userDetailsServiceImpl.register(newUser);
		if (newUser == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		UserDetails details = userDetailsServiceImpl.loadUserByUsername(newUser.getUsername());
		
		return new ResponseEntity<TokenDTO>(new TokenDTO(tokenUtils.generateToken(details)), HttpStatus.OK);
	}
	
}
