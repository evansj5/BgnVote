package repository;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findOneByUsername(String username);
	List<User> findAllByIdIn(Collection<Integer> ids, Sort sort);
}
