package service;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.BggGame;
import model.GameNightInstanceBoardGame;
import repository.IBoardGameGeekRepository;
import repository.IGameNightInstanceBoardGameRepository;
import viewmodel.game.GameViewModel;

@Component
public class GameService implements IGameService{
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	IBoardGameGeekRepository bggRepository;
	
	@Autowired
	IGameNightInstanceBoardGameRepository gameNightInstanceBoardGameRepository;

	@Override
	public List<GameViewModel> getGamesByBggUsername(String username) {
		List<BggGame> games = bggRepository.getCollectionByUsername(username);
		
		
		return games.stream()
				.map(g -> mapper.map(g, GameViewModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<GameViewModel> getNominatedGamesByGameNightInstanceId(String gameNightInstanceId) {
		List<GameNightInstanceBoardGame> nominatedBoardGameModels = gameNightInstanceBoardGameRepository.findAllByGameNightInstanceId(gameNightInstanceId);
		
		List<BggGame> bggModels = bggRepository.getGames(nominatedBoardGameModels.stream()
				.map(g -> g.getBoardGameBggId())
				.collect(Collectors.toList()));
		
		return bggModels.stream()
				.map(g -> mapper.map(g, GameViewModel.class))
				.collect(Collectors.toList());
	}	
}
