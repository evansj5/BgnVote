package viewmodel.user;

import java.util.List;

import model.BggGame;

public class ProfileViewModel {
	private List<BggGame> games;
	private UserViewModel user;
	
	public List<BggGame> getGames() {
		return games;
	}
	public void setGames(List<BggGame> games) {
		this.games = games;
	}
	public UserViewModel getUser() {
		return user;
	}
	public void setUser(UserViewModel user) {
		this.user = user;
	}	
}
