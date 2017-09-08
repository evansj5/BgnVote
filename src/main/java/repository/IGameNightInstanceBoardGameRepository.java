package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.GameNightInstanceBoardGame;

public interface IGameNightInstanceBoardGameRepository extends JpaRepository<GameNightInstanceBoardGame, String>{
	List<GameNightInstanceBoardGame> findAllByGameNightInstanceId(String id);
	GameNightInstanceBoardGame findOneByGameNightInstanceIdAndBoardGameBggId(String gameNightInstanceId, String boardGameBggId);
}
