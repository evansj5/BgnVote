package application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.IRegistrationService;

@RestController
@RequestMapping(path= "/rest/api/admin")
public class AdminController {
	@Autowired
	private IRegistrationService registrationService;
	
	@RequestMapping(method = RequestMethod.POST, value = "registrationKey", consumes = "application/json")
	public void issueRegistrationKey(@RequestBody List<String> emailAddresses) {
		this.registrationService.issueNewRegistrationKeys(emailAddresses);
	}
}
