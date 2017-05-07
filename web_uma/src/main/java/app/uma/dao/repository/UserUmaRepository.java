package app.uma.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.UserUma;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserUmaRepository extends CrudRepository<UserUma, Long> {
	
	@Query("select u from UserUma u where username=?1" )
	UserUma findByUsername(String username);
}
