package app.uma.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.uma.dao.entity.Permission;
import app.uma.dao.entity.UserUma;
import app.uma.dao.repository.PermissionRepository;
import app.uma.dao.repository.UserUmaRepository;

/**
 * Created by yangyibo on 17/1/18.
 */
@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口

	@Autowired
	UserUmaRepository userRepository;
	@Autowired
	PermissionRepository permissionRepository;

	public UserDetails loadUserByUsername(String username) {
		UserUma user = userRepository.findByUsername(username);
		if (user != null) {
			Iterable<Permission> permissions = permissionRepository.findByUserId(user.getId());
			ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList <>();
			for (Permission permission : permissions) {
				if (permission != null && permission.getName()!=null) {

					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
					grantedAuthorities.add(grantedAuthority);
				}
			}
			return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("admin: " + username + " do not exist!");
		}
	}
	

}
