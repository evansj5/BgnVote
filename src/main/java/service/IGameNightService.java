package service;

import java.util.List;

import model.GameNightUser;
import viewmodel.gamenight.GameNightViewModel;

public interface IGameNightService {
	List<GameNightViewModel> getAll();
	GameNightViewModel get(String id, boolean includeGames);
	GameNightViewModel create(GameNightViewModel gameNight);
	List<GameNightUser> getUsersForGameNight(String gameNightId);
	void delete(String id);
}
