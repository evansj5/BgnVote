package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.BggGame;
import repository.BoardGameGeekRepository;
import viewmodel.user.ProfileViewModel;
import viewmodel.user.UserViewModel;

@Component
public class ProfileService implements IProfileService{
	@Autowired
	private IUserService userService;
	
	@Autowired
	private BoardGameGeekRepository bggRepository;
	
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
}
