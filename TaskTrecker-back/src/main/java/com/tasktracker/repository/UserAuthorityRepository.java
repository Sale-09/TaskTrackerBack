package com.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasktracker.security.model.user.SecurityUserAuthority;

@Repository
public interface UserAuthorityRepository extends JpaRepository<SecurityUserAuthority, Long> {

}
