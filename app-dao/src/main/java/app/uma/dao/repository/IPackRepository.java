package app.uma.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.Pack;

public interface IPackRepository extends CrudRepository<Pack, String> {
	@Query("select p from Pack p where p.roleId=?1")
	List<Pack> getPacks(String rid);

	@Query("select p from Pack p where p.roleId=?1 and type=?2")
	Pack getPack(String rid , int type);

	Pack findByRoleIdAndType(String roleId, int type);
}
