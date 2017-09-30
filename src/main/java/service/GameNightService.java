package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import model.GameNight;
import model.GameNightInstance;
import model.GameNightInstanceState;
import model.GameNightUser;
import model.User;
import model.UserRole;
import repository.IGameNightRepository;
import repository.IGameNightUserRepository;
import viewmodel.game.GameViewModel;
import viewmodel.gamenight.GameNightViewModel;
import viewmodel.user.UserViewModel;

@Component
public class GameNightService implements IGameNightService {
	@Autowired
	IGameNightRepository gameNightRepository;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IGameNightInstanceService gameNightInstanceService;
	
	@Autowired
	IGameNightUserRepository gameNightUserRepository;
	
	@Autowired
	DozerBeanMapper mapper;
	
	public List<GameNightViewModel> getAll()	{
		List<GameNight> gameNights = this.gameNightRepository.findAll();
		List<GameNightViewModel> viewModels = new ArrayList<GameNightViewModel>();
		this.mapper.map(gameNights, viewModels);
		
		return viewModels;
	}
	
	public GameNightViewModel get(String id, boolean includeGames) {
		GameNight gameNightModel = gameNightRepository.findOne(id);
		GameNightViewModel gameNight = mapper.map(gameNightModel, GameNightViewModel.class);
		
		GameNightUser gameNightUser = gameNightUserRepository.findOneByGameNightIdAndUserId(id, userService.getCurrentUser().getId());
		
		gameNight.setOwned(gameNightUser.isOwner());
		if(includeGames) {
			// TODO		
		}		
		
		return gameNight;
	}

	@Override
	public GameNightViewModel create(GameNightViewModel gameNight) {
		GameNight gameNightModel = mapper.map(gameNight, GameNight.class);
		gameNightModel = gameNightRepository.save(gameNightModel);
		final GameNight innerGameNight = gameNightModel;
		
		UserViewModel currentUser = userService.getCurrentUser();
		
		gameNight.getInvitedUsers().stream().forEach(userId -> {
			inviteUser(userId, innerGameNight, userId == currentUser.getId());
		});
		
		GameNightInstance instance = new GameNightInstance(null, gameNightModel.getId(), gameNightModel.getStartDate(), GameNightInstanceState.WAITING_FOR_RSVP);
		gameNightInstanceService.scheduleNewInstance(instance, false);
		
		return mapper.map(gameNightModel, GameNightViewModel.class);
	}
	
	public List<GameNightUser> getUsersForGameNight(String gameNightId) {
		return this.gameNightUserRepository.findAllByGameNightId(gameNightId);
	}
	
	public void inviteUser(int userId, GameNight gameNight, boolean owner) {
		GameNightUser gameNightUser = new GameNightUser();
		User user = new User();
		user.setId(userId);
		gameNightUser.setOwner(true);
		gameNightUser.setGameNight(gameNight);
		gameNightUser.setUser(user);
		
		gameNightUserRepository.save(gameNightUser);
	}

	@Override
	public void delete(String id) {
		String currentUserName = this.userService.getCurrentUsername();
		GameNight gameNight = this.gameNightRepository.findById(id);
		UserDetails userDetails = this.userService.loadUserByUsername(currentUserName);
		
		if(!userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equalsIgnoreCase(UserRole.ROLE_ADMIN.toString()))) {
			throw new RuntimeException("Unauthorized");
		}
		
		this.gameNightInstanceService.deleteAllForGameNight(gameNight.getId());
		
		List<GameNightUser> users = this.gameNightUserRepository.findAllByGameNightId(id);
		this.gameNightUserRepository.delete(users);
		
		this.gameNightRepository.delete(gameNight);
	}	
}
