package com.springboot.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.entity.Profile;



@Service
public 
import com.springboot.dao.ProfileRepository;class ProfileServiceIpm implements ProfileService {
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	public ProfileServiceIpm(ProfileRepository theProfileRepository) {
		
		profileRepository = theProfileRepository;
	}

	@Override
	public List<Profile> findAll() {
		return profileRepository.findAll() ;
	}

	@Override
	public Profile findById(int theId) {
		Optional<Profile> result= profileRepository.findById(theId);
		
		Profile theProfile = null;

		if(result.isPresent()) {
			theProfile= result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find Profile id - " + theId);
		}
		return theProfile;
	}

	@Override
	public Profile save(Profile theProfile) {
		return profileRepository.save(theProfile);
	}

	@Override
	public void deleteById(int theId) {
		 profileRepository.deleteById(theId);
	}

	 
  @Transactional
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    Profile user = profileRepository.findByUsername(name)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + name));

    return UserDetailsImpl.build(user);
  }

}
