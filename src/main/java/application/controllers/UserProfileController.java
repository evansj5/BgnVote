package application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.IProfileService;
import viewmodel.user.ProfileViewModel;

@RestController
@RequestMapping(path= "/rest/api/profile")
public class UserProfileController {
	@Autowired
	private IProfileService profileService;
		
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public ProfileViewModel get() {
		return profileService.getMyProfile();
	}
}
