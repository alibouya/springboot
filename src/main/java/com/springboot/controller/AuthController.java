package com.springboot.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dao.ProfileRepository;
import com.springboot.dao.RoleRepository;
import com.springboot.entity.ERole;
import com.springboot.entity.Profile;
import com.springboot.entity.Role;
import com.springboot.request.LoginRequest;
import com.springboot.request.SignupRequest;
import com.springboot.response.JwtResponse;
import com.springboot.response.MessageResponse;
import com.springboot.security.jwt.JwtUtils;
import com.springboot.service.UserDetailsImpl;




@CrossOrigin(origins = "http://localhost:8080")

@RestController
@RequestMapping("/api/auth")
@ComponentScan(basePackageClasses = AuthController.class)

public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  ProfileRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

  //  SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    Profile user = new Profile(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
    	try {
    		Role userRole =  roleRepository.findByName(ERole.ROLE_CLIENT);
    		          
    		      roles.add(userRole);
    	}catch(RuntimeException e) {
    		throw new RuntimeException("Error: Role is not found.");
    	}
      
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
        	try {
        		 Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
        		 roles.add(adminRole);
        	}catch(RuntimeException e) {
                throw new RuntimeException("Error: Role is not found.");

        	}
         
          

          break;
        
        default:
        	try {
          Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT);
              
          roles.add(userRole);
        }catch( RuntimeException e){
        	throw new RuntimeException("Error: Role is not found.");
        }
        	
        }
      });
    }

    //user.setRole();
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
