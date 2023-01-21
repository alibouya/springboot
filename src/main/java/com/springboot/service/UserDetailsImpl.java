package com.springboot.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.entity.Profile;
@Service
public class UserDetailsImpl  implements UserDetails{
	 private static final long serialVersionUID = 1L;

	  private int id;

	  private String name;

	  private String email;
	  
     private int phone;
	  @JsonIgnore
	  private String password;

	  private Collection<? extends GrantedAuthority> authorities;

	  public UserDetailsImpl(int id, String name, String email, String password, int phone,
			  Collection<? extends GrantedAuthority> authorities) {
	    this.id = id;
	    this.name = name;
	    this.email = email;
	    this.password = password;
	    this.phone = phone;

	    this.authorities = authorities;
	  }

	  
	

	public static UserDetailsImpl build(Profile user) {
	    List<GrantedAuthority> authorities = user.getProfiles().stream()
	        .map(role -> new SimpleGrantedAuthority(role.getName()))
	        .collect(Collectors.toList());

	    return new UserDetailsImpl(
	        user.getId(), 
	        user.getName(), 
	        user.getEmail(),
	        user.getPassword(), user.getPhone(),
	        authorities);
	  }

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }

	  public int getId() {
	    return id;
	  }

	  public String getEmail() {
	    return email;
	  }

	  @Override
	  public String getPassword() {
	    return password;
	  }
	  public int getPhone() {
			return phone;
	  }
	  @Override
	  public String getUsername() {
	    return name;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }

	  @Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDetailsImpl user = (UserDetailsImpl) o;
	    return Objects.equals(id, user.id);
	  }

}
