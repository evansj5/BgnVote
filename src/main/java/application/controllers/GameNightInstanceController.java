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
import viewmodel.gamenight.GameNightInstanceResultsDto;
import viewmodel.gamenight.GameNightInstanceViewModel;
import viewmodel.gamenight.GameNightViewModel;
import viewmodel.gamenight.NominatedGameDto;
import viewmodel.gamenight.VotesDto;

@RestController
@RequestMapping(path = "/rest/api/gameNightInstance")
public class GameNightInstanceController {
	
	@Autowired
	private IGameNightInstanceService gameNightInstanceService;
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public List<GameNightInstanceViewModel> getAllByGameNightId(@RequestParam("gameNightId") String gameNightId) {
		return gameNightInstanceService.getAllInstancesForGameNight(gameNightId);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public GameNightInstanceViewModel get(@PathVariable String id) {
		return gameNightInstanceService.getInstance(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "nominate")
	public void nominateGames(@RequestBody List<NominatedGameDto> nominations) {
		gameNightInstanceService.nominateGames(nominations);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}/rsvp")
	public void rsvp(@RequestParam("coming") boolean coming, @PathVariable String id) {
		gameNightInstanceService.rsvp(id, coming);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/vote")
	public void vote(@RequestBody VotesDto votes) {
		gameNightInstanceService.addVotes(votes);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/getResults", produces="application/json")
	public GameNightInstanceResultsDto getVotingResults(@RequestParam("gameNightInstanceId") String gameNightInstanceId) {
		return gameNightInstanceService.getVotingResults(gameNightInstanceId);
	}
}
