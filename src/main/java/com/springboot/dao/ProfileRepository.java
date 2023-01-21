package com.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Profile;


@Repository("ProfileRepository")
@NoRepositoryBean
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
	public List<Profile> findAll();
	public Optional<Profile> findByUsername(String name);

	public  Boolean existsByUsername(String name);

	 public Boolean existsByEmail(String email);
}

