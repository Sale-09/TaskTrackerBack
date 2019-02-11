package com.tasktracker.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tasktracker.repository.AuthorityRepository;
import com.tasktracker.repository.UserAuthorityRepository;
import com.tasktracker.repository.UserRepository;
import com.tasktracker.security.model.user.SecurityUser;
import com.tasktracker.security.model.user.SecurityUserAuthority;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private UserAuthorityRepository userAuthorityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecurityUser user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username)); 
		} else {
			List<GrantedAuthority> grantedAuthorities = user.getUserAuthorities().stream()
		            .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getName()))
		            .collect(Collectors.toList());
			
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(),
					user.getPassword(),
					grantedAuthorities);
		}
	}
	
	public SecurityUser register(final SecurityUser user) {
		if (userRepository.findByUsername(user.getUsername()) != null) {
			return null;
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		SecurityUser su = userRepository.save(user);
		SecurityUserAuthority sua = new SecurityUserAuthority();
		sua.setUser(su);
		sua.setAuthority(authorityRepository.findOne((long) 2));
		userAuthorityRepository.save(sua);
		return su;
	}


}
