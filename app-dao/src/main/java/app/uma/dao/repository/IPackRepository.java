package app.uma.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.Pack;

public interface IPackRepository extends CrudRepository<Pack, String> {
	@Query("select p from Pack p where p.roleId=?")
	  List<Pack> getPacks(String rid);
}
