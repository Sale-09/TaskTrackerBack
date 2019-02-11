package com.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasktracker.security.model.user.SecurityAuthority;

@Repository
public interface AuthorityRepository extends JpaRepository<SecurityAuthority, Long> {

}
