/**
 *
 * author: shanonder
 * date: 2017年5月5日 上午12:45:05
 *
 */
package app.uma.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.Permission;

public interface PermissionRepository  extends CrudRepository<Permission, Long> {

	@Query("from Permission where uid=?1" )
	Iterable<Permission> findByUserId(String uid);
	
}
