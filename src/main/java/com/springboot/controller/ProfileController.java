package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Profile;
import com.springboot.service.ProfileService;







@RestController 
@RequestMapping("/profiles")
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	
	public ProfileController(ProfileService theProfileService) {
		profileService = theProfileService;
	}

     /* public String listProfiles(Model theModel) {
		
		// get employees from db
		List<Profile> theProfiles = profileService.findAll();
		
		theModel.addAttribute("profiles", theProfiles);
		
		return "profiles/list_profiles";
	}*/
	@GetMapping("/list")
public List<Profile> listProfiles() {
		//List<Profile> list = new ArrayList<Profile>();
	//	profileService.findAll().forEach(list::add);
	    return profileService.findAll();
		// get employees from db
		//return profileService.findAll();
		
		
	}
	///////////////////////////////////////////////////////////////////////
	/*
	List<PersonInfo> list = new ArrayList<>();
    personRepo.findAll().forEach(list::add);
    return list;
	*/
    @GetMapping("/client/{id}")
    public Profile getProfileById(@PathVariable(value = "id") int profileId) {
        
          
        return profileService.findById(profileId);
    }
    @PostMapping("/saveClient")
    public Profile createClient(@RequestBody Profile profile) {
    	return profileService.save(profile);
    }
    @DeleteMapping("/profile/{id}")
    public List<Profile> deleteProfile(@PathVariable(value = "id") int profileId) {
    	List<Profile> list = new ArrayList<>();
    	profileService.findAll().forEach(list::add);
    	profileService.deleteById(profileId);
        return list;
    	
    }

}
