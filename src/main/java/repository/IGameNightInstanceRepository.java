package repository;

import model.GameNightInstance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IGameNightInstanceRepository extends JpaRepository <GameNightInstance, String> {
	GameNightInstance findTopByGameNightIdOrderByDateAsc(String gameNightId);
	
	List<GameNightInstance> findAllByGameNightId(String gameNightId);
}
