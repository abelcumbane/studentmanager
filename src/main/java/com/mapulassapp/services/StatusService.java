package com.mapulassapp.services;

import java.util.List;

import com.mapulassapp.models.Status;

public interface StatusService {

	public void save(Status status);
	public List<Status> findAll();
}
