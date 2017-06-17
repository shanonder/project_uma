package app.uma.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface IUserRepository extends CrudRepository<User, String> {
//	@Query("select u from User u where u.platId=?1 and u.serverId=?2 and u.openId=?3")
//	  User getUserById( int platId ,int serverId , String openId);
	@Query("select u from User u where u.platId=?1 and u.serverId=?2 and u.openId=?3")
	User findByPlatIdAndServerIdAndOpenId(int platId ,int serverId , String openId);
}
