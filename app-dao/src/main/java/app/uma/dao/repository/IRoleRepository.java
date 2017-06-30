package app.uma.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.Role;

public interface IRoleRepository extends CrudRepository<Role, String> {
	@Query("select r from Role r where r.uid=?1")
	Role getRoleByUid(String uid);

//	@Query("select r from Role r where r.name=?1")
//	Boolean hasRole(String name);
	
	@Query("select r from Role r where r.name=?1")
	Role findOneByRoleName(String name);
	 
}
