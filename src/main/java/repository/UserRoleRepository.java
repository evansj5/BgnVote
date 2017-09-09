package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.UserRoleModel;

public interface UserRoleRepository extends JpaRepository<UserRoleModel, Integer> {

}
