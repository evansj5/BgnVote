package service;

import java.util.List;

import model.GameNightInstance;
import viewmodel.gamenight.GameNightInstanceResultsDto;
import viewmodel.gamenight.GameNightInstanceViewModel;
import viewmodel.gamenight.NominatedGameDto;
import viewmodel.gamenight.ScheduleGameNightInstanceDto;
import viewmodel.gamenight.VotesDto;

public interface IGameNightInstanceService {
	GameNightInstance getNextScheduledInstanceForGameNight(String gameNightId);

	GameNightInstance scheduleNewInstance(GameNightInstance newInstance, boolean sendEmail);

	List<GameNightInstanceViewModel> getAllInstancesForGameNight(String gameNightId);

	GameNightInstanceViewModel getInstance(String id);
	
	void nominateGames(List<NominatedGameDto> nominations);
	
	void rsvp(String gameNightInstanceId, boolean coming);
	
	void addVotes(VotesDto votes);
	
	GameNightInstanceResultsDto getVotingResults(String gameNightInstanceId);
	
	void deleteAllForGameNight(String id);
	
	GameNightInstance scheduleNewInstance(ScheduleGameNightInstanceDto scheduleDto);
}