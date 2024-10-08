package repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import entity.User;

@Repository
public interface Userrepository extends CrudRepository<User,Integer>{
	Optional<User>findByEmail(String Email);

}
