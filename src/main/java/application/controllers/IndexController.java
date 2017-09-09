package application.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.IRegistrationService;
import viewmodel.user.RegisterUserDto;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private IRegistrationService registrationService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping(path="/register", method = RequestMethod.POST)
	public String register(@ModelAttribute RegisterUserDto registrationDto) {
		registrationService.registerUser(registrationDto);
		return "login";
	}
}
