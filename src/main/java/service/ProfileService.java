package service;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.BggGame;
import model.User;
import repository.BoardGameGeekRepository;
import repository.UserRepository;
import viewmodel.user.ProfileViewModel;
import viewmodel.user.UserViewModel;

@Component
public class ProfileService implements IProfileService{
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardGameGeekRepository bggRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	/**
	 * Gets a list of the current users games.
	 * @return list of games
	 */
	@Override
	public ProfileViewModel getMyProfile() {
		UserViewModel user = userService.getCurrentUser();
		List<BggGame> games = bggRepository.getCollectionByUsername(user.getBggUsername());
		
		ProfileViewModel profile = new ProfileViewModel();
		profile.setUser(user);
		profile.setGames(games);
		
		return profile;
	}

	@Override
	public void updateProfile(ProfileViewModel profile) {
		UserViewModel user = profile.getUser();
		User userModel = userRepository.getOne(user.getId());
		
		mapper.map(user, userModel);
		userRepository.save(userModel);		
	}
	
	
}
