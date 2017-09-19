package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.UserRoleModel;

public interface UserRoleRepository extends JpaRepository<UserRoleModel, Integer> {	
	List<UserRoleModel> findAllByUsername(String username);
}
