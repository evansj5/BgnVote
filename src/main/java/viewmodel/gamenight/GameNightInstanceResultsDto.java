package viewmodel.gamenight;

import java.util.List;

import viewmodel.user.UserViewModel;

public class GameNightInstanceResultsDto {
	private List<UserViewModel> users;
	
	private List<GameNightInstanceGameResultDto> games;

	public List<UserViewModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserViewModel> users) {
		this.users = users;
	}

	public List<GameNightInstanceGameResultDto> getGames() {
		return games;
	}

	public void setGames(List<GameNightInstanceGameResultDto> games) {
		this.games = games;
	}
}
