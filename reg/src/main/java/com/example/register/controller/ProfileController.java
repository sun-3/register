package com.example.register.controller;

import com.example.register.model.FormData;
import com.example.register.model.Profile;
import com.example.register.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService service;
	
	@PostMapping("/api")
	public void addProfile(@ModelAttribute FormData formData) throws IOException {
		Profile profile = new Profile();
		profile.setName(formData.getName());
		profile.setUsername(formData.getUsername());
		profile.setEmail(formData.getEmail());
		profile.setMobile(formData.getMobile());
		profile.setGender(formData.getGender());
		profile.setSkills(formData.getSkills());
		profile.setState(formData.getState());
		profile.setProfilePic(formData.getFile().getBytes().toString());
		service.add(profile);

		System.out.println(formData.getUsername()+"get");

	}

	@GetMapping("/api/{username}")
	public Profile getProfile(@PathVariable String username) {

		return service.getProfile(username);
	}
	
	@GetMapping("/api")
	public List<Profile> getAllProfiles(){

		return service.getAllProfiles();
	}

	@GetMapping("/view/{username}")
	public String getProfileData(@PathVariable String username) {

		return service.showProfileData(username);
	}
	@PutMapping("/api")
	public void editProfile(@RequestParam @Valid String name,
						   @RequestParam @Valid String username,
						   @RequestParam @Valid String email,
						   @RequestParam @Valid Long mobile,
						   @RequestParam @Valid String gender,
						   @RequestParam @Valid String skills,
						   @RequestParam @Valid String state,
						   @RequestParam @Valid MultipartFile file) throws IOException {
		Profile profile = new Profile();
		profile.setName(name);
		profile.setUsername(username);
		profile.setEmail(email);
		profile.setMobile(mobile);
		profile.setGender(gender);
		profile.setSkills(skills);
		profile.setState(state);
		profile.setProfilePic(file.getBytes().toString());
		service.add(profile);
	}
	@GetMapping
	public String registerPage(){
		return service.showRegisterPage();
	}
	@GetMapping("/{username}")
	public String editPage(@PathVariable String username){

		return  service.showEditPage(username);
	}
}
