package application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.IGameNightInstanceService;
import service.IGameNightService;
import viewmodel.gamenight.GameNightViewModel;
import viewmodel.gamenight.ScheduleGameNightInstanceDto;

@RestController
@RequestMapping(path="/rest/api/gameNight")
public class GameNightController {
	@Autowired
	IGameNightService gameNightService;
	
	@Autowired
	IGameNightInstanceService gameNightInstanceService;
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public List<GameNightViewModel> get() {
		return this.gameNightService.getAll();
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.GET, produces="application/json")
	public GameNightViewModel get(boolean includeGames, @PathVariable String id) {
		return this.gameNightService.get(id, includeGames);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public GameNightViewModel create(@RequestBody GameNightViewModel gameNightViewModel) {
		return this.gameNightService.create(gameNightViewModel);
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE) 
	public void delete(@PathVariable String id) {
		this.gameNightService.delete(id);
	}
	
	@RequestMapping(path="/schedule", method = RequestMethod.POST)
	public void scheduleNewInstance(@RequestBody ScheduleGameNightInstanceDto scheduleDto) {
		this.gameNightInstanceService.scheduleNewInstance(scheduleDto);
	}
}
