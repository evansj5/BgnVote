package service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.BggGame;
import model.GameNight;
import model.GameNightInstance;
import model.GameNightInstanceBoardGame;
import model.GameNightInstanceBoardGameVote;
import model.GameNightInstanceState;
import model.GameNightInstanceUser;
import model.GameNightInstanceUserId;
import model.GameNightUser;
import model.User;
import repository.IBoardGameGeekRepository;
import repository.IGameNightInstanceBoardGameRepository;
import repository.IGameNightInstanceBoardGameVoteRepository;
import repository.IGameNightInstanceRepository;
import repository.IGameNightInstanceUserRepository;
import repository.IGameNightRepository;
import repository.UserRepository;
import viewmodel.gamenight.GameNightInstanceGameResultDto;
import viewmodel.gamenight.GameNightInstanceResultsDto;
import viewmodel.gamenight.GameNightInstanceViewModel;
import viewmodel.gamenight.NominatedGameDto;
import viewmodel.gamenight.ScheduleGameNightInstanceDto;
import viewmodel.gamenight.VotesDto;
import viewmodel.user.UserViewModel;

@Component
public class GameNightInstanceService implements IGameNightInstanceService {
	@Autowired
	IGameNightInstanceRepository gameNightInstanceRepository;
	
	@Autowired
	IGameNightInstanceBoardGameRepository gameNightInstanceBoardGameRepository;
	
	@Autowired
	IGameNightInstanceUserRepository gameNightInstanceUserRepository;
	
	@Autowired
	IGameNightInstanceBoardGameVoteRepository gameNightInstanceBoardGameVoteRepository;
	
	@Autowired
	IGameNightService gameNightService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IGameNightInstanceBoardGameVoteRepository votesRepository;
	
	@Autowired
	IBoardGameGeekRepository bggRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	IGameNightRepository gameNightRepository;
	
	@Autowired
	DozerBeanMapper mapper;
	
	/* (non-Javadoc)
	 * @see service.IGameNightInstanceService#getNextScheduledInstanceForGameNight(java.lang.String)
	 */
	@Override
	public GameNightInstance getNextScheduledInstanceForGameNight(String gameNightId) {
		return gameNightInstanceRepository.findTopByGameNightIdOrderByDateAsc(gameNightId);
	}
	
	@Override
	public GameNightInstance scheduleNewInstance(GameNightInstance newInstance, boolean sendEmail) {
		newInstance.setState(GameNightInstanceState.WAITING_FOR_RSVP);
		GameNightInstance createdInstance = gameNightInstanceRepository.save(newInstance);
		
		List<GameNightUser> gameNightUsers = gameNightService.getUsersForGameNight(createdInstance.getGameNight().getId());
		
		gameNightUsers.stream()
			.forEach(gameNightUser -> {
				GameNightInstanceUser gameNightInstanceUser = mapper.map(gameNightUser, GameNightInstanceUser.class);
				gameNightInstanceUser.getId().setGameNightInstanceId(createdInstance.getId());
				gameNightInstanceUserRepository.save(gameNightInstanceUser);
			});
		
		if(sendEmail) {
			// TODO send email
		}		
		
		return createdInstance;
	}
	
	public GameNightInstance scheduleNewInstance(ScheduleGameNightInstanceDto scheduleDto) {
		GameNightInstance instance = new GameNightInstance(null, scheduleDto.getGameNightId(), scheduleDto.getStartDate(), GameNightInstanceState.WAITING_FOR_RSVP);
		GameNight gameNight = gameNightRepository.findOne(scheduleDto.getGameNightId());
		instance.setGameNight(gameNight);
		return this.scheduleNewInstance(instance, true);		
	}
	
	@Override
	public List<GameNightInstanceViewModel> getAllInstancesForGameNight(String gameNightId) {
		List<GameNightInstance> instances = gameNightInstanceRepository.findAllByGameNightId(gameNightId);
		
		return instances.stream()
				.map(gni -> mapper.map(gni, GameNightInstanceViewModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public GameNightInstanceViewModel getInstance(String id) {
		GameNightInstanceViewModel viewModel = mapper.map(gameNightInstanceRepository.findOne(id), GameNightInstanceViewModel.class);		
		GameNightInstanceUser userJoin = gameNightInstanceUserRepository.findOne(new GameNightInstanceUserId(userService.getCurrentUser().getId(), id));
		
		if(userJoin != null) {
			mapper.map(userJoin, viewModel);
		}
		
		return viewModel;
	}
	
	public void updateState(String gameNightInstanceId, GameNightInstanceState newState) {
		GameNightInstance instance = gameNightInstanceRepository.findOne(gameNightInstanceId);
		instance.setState(newState);
		gameNightInstanceRepository.save(instance);
	}
	
	@Override
	public void nominateGames(List<NominatedGameDto> nominations) {
		List<GameNightInstanceBoardGame> domainModels = nominations
			.stream()
			.map(nomination -> mapper.map(nomination, GameNightInstanceBoardGame.class))
			.collect(Collectors.toList());
		
		User user = new User();
		
		int userId = userService.getCurrentUser().getId();
		user.setId(userId);
		
		GameNightInstanceUser userInfo = this.getUserInfoForGameNightInstance(nominations.stream().findFirst().get().getGameNightInstanceId());
		
		if(userInfo.hasNominated()) {
			// do something here...
			return;
		}
		
		List<GameNightInstanceBoardGame> games = 
				gameNightInstanceBoardGameRepository.findAllByGameNightInstanceId(domainModels.stream().findFirst().get().getGameNightInstance().getId());
		
		domainModels.forEach(gnibg -> {
			gnibg.setUser(user);
			
			if(!games.stream().anyMatch(g -> g.getBoardGameBggId() == gnibg.getBoardGameBggId())) {
				gameNightInstanceBoardGameRepository.save(gnibg);
			}			
		});
		
		GameNightInstanceUserId id = new GameNightInstanceUserId(userId, nominations.stream().findFirst().orElse(null).getGameNightInstanceId()); 
		GameNightInstanceUser gameNightInstanceUser = gameNightInstanceUserRepository.findOne(id);
		
		if(gameNightInstanceUser == null) {
			gameNightInstanceUser = new GameNightInstanceUser();
			gameNightInstanceUser.setId(id);
			gameNightInstanceUser.setRsvpd(true);
			gameNightInstanceUser.setComing(true);
		}
		
		gameNightInstanceUser.setNominated(true);
		
		gameNightInstanceUserRepository.save(gameNightInstanceUser);
		
		List<GameNightInstanceUser> users = gameNightInstanceUserRepository.findAllByIdGameNightInstanceId(id.getGameNightInstanceId());
		
		if(users != null && users.stream().allMatch(u -> u.hasNominated() || (u.hasRsvpd() && !u.isComing()))) {
			updateState(id.getGameNightInstanceId(), GameNightInstanceState.VOTING);
		}
		this.checkIfNominatingIsFinished(id.getGameNightInstanceId());
	}
	
	public void rsvp(String gameNightInstanceId, boolean coming) {
		GameNightInstanceUser userInfo = getUserInfoForGameNightInstance(gameNightInstanceId);
		
		userInfo.setRsvpd(true);
		userInfo.setComing(coming);
		
		this.gameNightInstanceUserRepository.save(userInfo);
		
		this.checkIfNominatingIsFinished(gameNightInstanceId);
	}
	
	public GameNightInstanceUser getUserInfoForGameNightInstance(String gameNightInstanceId) {
		GameNightInstanceUserId id = new GameNightInstanceUserId();
		id.setGameNightInstanceId(gameNightInstanceId);
	    
		int userId = this.userService.getCurrentUser().getId();
		id.setUserId(userId);
		
		return this.gameNightInstanceUserRepository.findOne(id);
	}
	
	public void addVotes(VotesDto votes) {
		GameNightInstanceUser gameNightInstanceUser = this.getUserInfoForGameNightInstance(votes.getGameNightInstanceId());
		
		if(gameNightInstanceUser.hasVoted()) {
			// TODO do some sort of messaging back to user
			return;
		}
		
		User user = new User();
		
		user.setId(this.userService.getCurrentUser().getId());
		
		votes.getVotes().stream().forEach(vote -> {
			GameNightInstanceBoardGameVote voteModel = new GameNightInstanceBoardGameVote();
			voteModel.setUser(user);
			voteModel.setVote(vote.getVote());
			GameNightInstanceBoardGame game = this.gameNightInstanceBoardGameRepository.findOneByGameNightInstanceIdAndBoardGameBggId(votes.getGameNightInstanceId(), vote.getGameId());
			voteModel.setGame(game);
			votesRepository.save(voteModel);
		});
		
		gameNightInstanceUser.setVoted(true);
		this.gameNightInstanceUserRepository.save(gameNightInstanceUser);
		
		this.checkIfVotingIsFinished(votes.getGameNightInstanceId());
	}
	
	private void checkIfVotingIsFinished(String gameNightInstanceId) {
		List<GameNightInstanceUser> users = this.gameNightInstanceUserRepository.findAllByIdGameNightInstanceId(gameNightInstanceId);
		
		boolean allVoted = users.stream()
							.allMatch(user -> user.hasVoted() || (user.hasRsvpd() && !user.isComing()));
		
		if(allVoted) {
			updateState(gameNightInstanceId, GameNightInstanceState.FINALIZED);
			calculateGameStats(gameNightInstanceId);
		}
	}
	
	private void checkIfNominatingIsFinished(String gameNightInstanceId) {
		List<GameNightInstanceUser> users = this.gameNightInstanceUserRepository.findAllByIdGameNightInstanceId(gameNightInstanceId);
		
		boolean allRsvpd = users.stream()
				.allMatch(user -> (user.hasRsvpd() && !user.isComing()) || user.hasNominated());
		
		if(allRsvpd) {
			updateState(gameNightInstanceId, GameNightInstanceState.VOTING);
		}
	}
	
	private void calculateGameStats(String gameNightInstanceId) {
		List<GameNightInstanceBoardGame> games = this.gameNightInstanceBoardGameRepository.findAllByGameNightInstanceId(gameNightInstanceId);
		
		games.stream().forEach(game -> {
			double averageVote = getAverageVote(game);
			game.setAverageVote(averageVote);			
		});
		
		this.gameNightInstanceBoardGameRepository.save(games);
	}
	
	private double getAverageVote(GameNightInstanceBoardGame game) {
		List<GameNightInstanceBoardGameVote> votes = this.votesRepository.findAllByGameIdOrderByUserIdAsc(game.getId());
		
		int numVotes = votes.size();
		
		int sumVotes = votes.stream()
							.mapToInt(vote -> vote.getVote())
							.sum();
		
		return ((double)sumVotes) / ((double) numVotes);
	}

	public GameNightInstanceResultsDto getVotingResults(String gameNightInstanceId) {
		List<GameNightInstanceBoardGame> gameNightInstanceGames = this.gameNightInstanceBoardGameRepository.findAllByGameNightInstanceId(gameNightInstanceId);
		List<String> bggIds = gameNightInstanceGames.stream()
								.map(game -> game.getBoardGameBggId())
								.collect(Collectors.toList());
		List<BggGame> bggGames = this.bggRepository.getGames(bggIds);
		
		List<GameNightInstanceUser> users = this.gameNightInstanceUserRepository.findAllByIdGameNightInstanceIdOrderByIdUserIdAsc(gameNightInstanceId);
		
		List<UserViewModel> userViewModels = this.userService.getAllSortByIdAsc(users.stream()
				.filter(user -> user.hasRsvpd() && user.isComing())
				.map(user -> user.getId().getUserId()).collect(Collectors.toList()));
		
		GameNightInstanceResultsDto resultDto = new GameNightInstanceResultsDto();
		userViewModels.sort(Comparator.comparingInt(UserViewModel::getId));
		resultDto.setUsers(userViewModels);
		
		List<GameNightInstanceGameResultDto> gameResultDtos = gameNightInstanceGames.stream()
			.map(game -> {
				GameNightInstanceGameResultDto gameResult = new GameNightInstanceGameResultDto();
				gameResult.setAverageVote(game.getAverageVote());
				BggGame matchingGame = bggGames.stream().filter(bggGame -> bggGame.getId().equalsIgnoreCase(game.getBoardGameBggId())).findFirst().get();
				gameResult.setName(matchingGame.getName());
				gameResult.setVotes(retrieveVotes(game));
				return gameResult;
			})
			.collect(Collectors.toList());
		
		gameResultDtos.sort(Comparator.comparingDouble(GameNightInstanceGameResultDto::getAverageVote));
		
		resultDto.setGames(gameResultDtos);
		
		return resultDto;
	}
	
	
	
	@Override
	public void deleteAllForGameNight(String id) {
		List<GameNightInstanceViewModel> instances = this.getAllInstancesForGameNight(id);
		instances.forEach(i -> deleteInstance(i.getId()));		
	}
	
	private void deleteInstance(String instanceId) {
		GameNightInstance instance = this.gameNightInstanceRepository.findOne(instanceId);
		List<GameNightInstanceBoardGame> games = this.gameNightInstanceBoardGameRepository.findAllByGameNightInstanceId(instanceId);
		
		games.forEach(g -> {
			List<GameNightInstanceBoardGameVote> votes = this.gameNightInstanceBoardGameVoteRepository.findAllByGameIdOrderByUserIdAsc(g.getId());
			votes.forEach(v -> {
				this.gameNightInstanceBoardGameVoteRepository.delete(v.getId());
			});
			this.gameNightInstanceBoardGameRepository.delete(g);
		});
		
		List<GameNightInstanceUser> users = this.gameNightInstanceUserRepository.findAllByIdGameNightInstanceId(instanceId);
		users.forEach(u -> this.gameNightInstanceUserRepository.delete(u));
		
		this.gameNightInstanceRepository.delete(instance);
	}

	private Map<Integer, Integer> retrieveVotes(GameNightInstanceBoardGame game) {
		List<GameNightInstanceBoardGameVote> votes = this.votesRepository.findAllByGameIdOrderByUserIdAsc(game.getId());
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (GameNightInstanceBoardGameVote vote : votes) {
			map.put(vote.getUser().getId(), vote.getVote());
		}
		return map;
	}
}
