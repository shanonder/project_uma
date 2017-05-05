/**
 *
 * author: shanonder
 * date: 2017年5月5日 上午12:45:05
 *
 */
package app.uma.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.Permission;

public interface PermissionRepository  extends CrudRepository<Permission, Long> {

	@Query("from permission where pid=?1" )
	List<Permission> findByUserId(String pid);
	
}
