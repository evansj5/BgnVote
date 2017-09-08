package service;

import java.util.List;

import viewmodel.game.GameViewModel;

public interface IGameService {
	List<GameViewModel> getGamesByBggUsername(String username);
	
	List<GameViewModel> getNominatedGamesByGameNightInstanceId(String gameNightInstanceId);
}
