package com.mapulassapp.services;

import java.util.List;

import com.mapulassapp.models.Student;

public interface StudentService {
	
	public void save(Student student);
	public void remove(Student student);
	public List<Student> findAll();
	public List<Student> find(String substring);

}
