package com.springboot.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
@Data
@Entity
@Table(name="profile")
public class Profile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	@NotEmpty(message = "*Please provide your name")
	private String Name;
	
	
	@Column(name="email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String Email;
	
	@Column(name="password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String Password;
	
	@Column(name="role")
	private String Role;
    
	@Column(name="phone")
	private int Phone;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "profile_id"),
	inverseJoinColumns = @JoinColumn(name = "roles_id"))
	private Set<Profile> Profiles;
	

	
	
    public Profile(String name, String email, String password, String role,int phone) {
		
    	this.Name = name;
    	this.Email = email;
    	this.Password = password;
    	this.Role = role;
    	this.Phone=phone;
	}

	public Profile(int id, String name, String email, String password, String role,int phone) {
		
		this.id = id;
		this.Name = name;
		this.Email = email;
		this.Password = password;
		this.Role = role;
    	this.Phone=phone;

	}

	public Profile(String name, String email, String password) {
		this.Name = name;
		this.Email = email;
		this.Password = password;
	}
	
	public int getPhone() {
		return Phone;
	}

	public void setPhone(int phone) {
		Phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		this.Role = role;
	}
	
	public Set<Profile> getProfiles() {
		return Profiles;
	}

	public void setProfiles(Set<Profile> profiles) {
		Profiles = profiles;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", Name=" + Name + ", Email=" + Email + ", Password=" + Password + ", Role=" + Role + ", Phone=" + Phone
				+ "]";
	}
}

