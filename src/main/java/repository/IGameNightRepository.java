package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import model.GameNight;

public interface IGameNightRepository extends JpaRepository<GameNight, String>{
	<S extends GameNight> S save(S entity);
	
	GameNight findById(@Param("id") String id);
	
	
	List<GameNight> findAll();
		
	
	void delete(GameNight entity);
	
	
	boolean exists(@Param("id")String id);
}
