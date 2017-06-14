package app.uma.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface IUserRepository extends CrudRepository<User, String> {
	@Query("select u from User u where u.platId=?2 and u.plat_key=?1")
	  List<User> getUserById(String platKey , int platId);
}
