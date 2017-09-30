package service;

import viewmodel.user.ProfileViewModel;

public interface IProfileService {

	public ProfileViewModel getMyProfile();	
	void updateProfile(ProfileViewModel profile);
	
}
