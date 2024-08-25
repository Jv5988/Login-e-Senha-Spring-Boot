package com.projcrud.login_senha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projcrud.login_senha.entity.User;
@Repository
public interface User_Repository extends JpaRepository<User, Long> {
	
	User findByEmail(String email); //select * from ... where email = ... 
}