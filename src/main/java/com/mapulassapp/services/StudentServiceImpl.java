package com.mapulassapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapulassapp.models.Student;
import com.mapulassapp.repositorys.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void save(Student student) {
		studentRepository.save(student);
		
	}

	@Override
	public void remove(Student student) {
		studentRepository.delete(student);	
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> find(String substring) {
		return studentRepository.findStudents(substring);
	}

}
