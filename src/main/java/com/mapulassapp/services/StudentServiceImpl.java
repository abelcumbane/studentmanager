package com.mapulassapp.services;

import java.util.List;
import java.util.Optional;

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

	@Override
    public void update(Student student) {
        if (studentRepository.existsById(student.getId())) {
            studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    @Override
    public Student findById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.orElse(null);
    }

}
