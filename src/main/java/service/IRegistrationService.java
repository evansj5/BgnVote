package service;

import java.util.List;

import viewmodel.user.RegisterUserDto;

public interface IRegistrationService {

	void registerUser(RegisterUserDto registerUserDto);
	
	void issueNewRegistrationKeys(List<String> emailAddresses);

}