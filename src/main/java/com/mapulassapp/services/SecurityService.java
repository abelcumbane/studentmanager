package com.mapulassapp.services;

import com.mapulassapp.models.User;

public interface SecurityService {
	public void save(String username, String password);
	public void logout();

}
