package application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.IGameNightInstanceService;
import service.IGameNightService;
import service.IUserService;
import viewmodel.gamenight.GameNightInstanceViewModel;
import viewmodel.gamenight.GameNightViewModel;
import viewmodel.gamenight.NominatedGameDto;
import viewmodel.user.UserViewModel;

@RestController
@RequestMapping(path = "/rest/api/users")
public class UserController {	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public List<UserViewModel> getAll() {
		return this.userService.getAll();
	}
}
