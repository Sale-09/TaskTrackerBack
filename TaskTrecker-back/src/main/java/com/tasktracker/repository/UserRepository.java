package com.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasktracker.security.model.user.SecurityUser;

@Repository
public interface UserRepository extends JpaRepository<SecurityUser, Long> {
	
	public SecurityUser findByUsername(String username);
	
}
