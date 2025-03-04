package com.mapulassapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapulassapp.models.Status;
import com.mapulassapp.repositorys.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	private StatusRepository statusRepository;

	@Override
	public void save(Status status) {
		statusRepository.save(status);
		
	}

	@Override
	public List<Status> findAll() {
		return statusRepository.findAll();
	}

}
