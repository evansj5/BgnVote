package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.GameNightInstanceUser;
import model.GameNightInstanceUserId;

public interface IGameNightInstanceUserRepository extends JpaRepository <GameNightInstanceUser, GameNightInstanceUserId> {
	List<GameNightInstanceUser> findAllByIdGameNightInstanceId(String gameNightInstanceId);
	List<GameNightInstanceUser> findAllByIdGameNightInstanceIdOrderByIdUserIdAsc(String gameNightInstanceId);
}
