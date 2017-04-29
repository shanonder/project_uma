package app.uma.dao.repository;

import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface IUserRepository extends CrudRepository<User, Long> {

}
