package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.GameNightInstanceBoardGameVote;

public interface IGameNightInstanceBoardGameVoteRepository extends JpaRepository <GameNightInstanceBoardGameVote, String> {
	List<GameNightInstanceBoardGameVote> findAllByGameIdOrderByUserIdAsc(String gameId);
}
