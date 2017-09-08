package repository;

import java.util.List;

import model.BggGame;

public interface IBoardGameGeekRepository {
	public List<BggGame> getCollectionByUsername(String username);
	List<BggGame> getGames(List<String> bggGameIds);
}
