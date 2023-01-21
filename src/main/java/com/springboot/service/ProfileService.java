package com.springboot.service;

import java.util.List;

import com.springboot.entity.Profile;




public interface ProfileService {

	public List<Profile> findAll();
	
	public Profile findById(int theId);
	
	public Profile save(Profile theProfile);
	
	public void deleteById(int theId);
}
