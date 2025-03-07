package com.mapulassapp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mapulassapp.models.User;

public interface SecurityRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.username=:username")
	User findByUsername(String username);

}
