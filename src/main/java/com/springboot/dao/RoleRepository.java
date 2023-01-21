package com.springboot.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.springboot.entity.ERole;
import com.springboot.entity.Role;
@Repository("RoleRepository")
@NoRepositoryBean
public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByRole(String role);

	public Role findByName(ERole roleUser);

}


