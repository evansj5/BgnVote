package application.controllers;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import model.BggGame;
import repository.IBoardGameGeekRepository;
import service.IGameNightInstanceService;
import service.IGameService;
import viewmodel.game.GameViewModel;

@RestController
@RequestMapping(path= "/rest/api/games")
public class GameController {
	@Autowired
	public IBoardGameGeekRepository bggRepository;
	
	@Autowired
	public IGameService gameService;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<GameViewModel> getGamesByBggUsername(@RequestParam(value="username") String username) {
		return gameService.getGamesByBggUsername(username);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json", value="/gameNightInstance")
	public @ResponseBody List<GameViewModel> getGamesForGameNightInstance(@RequestParam(value="id") String gameNightInstanceId) {
		return gameService.getNominatedGamesByGameNightInstanceId(gameNightInstanceId);
	}
}
