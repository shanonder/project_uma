package app.uma.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.Role;

public interface IRoleRepository extends CrudRepository<Role, String> {
	@Query("select r from Role r where r.uid=?")
	List<Role> getRoleList(String uid);
	 
}
