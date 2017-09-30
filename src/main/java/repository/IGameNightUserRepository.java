package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.GameNightUser;

public interface IGameNightUserRepository extends JpaRepository <GameNightUser, String> {
	List<GameNightUser> findAllByGameNightId(String gameNightId);
	GameNightUser findOneByGameNightIdAndUserId(String gameNightId, int userId);
}
