package com.mapulassapp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapulassapp.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
	
	//All the CRUD operations are implemented by default

}
