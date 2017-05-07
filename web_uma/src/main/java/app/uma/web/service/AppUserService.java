/**
 *
 * author: shanonder
 * date: 2017年5月6日 下午12:09:19
 *
 */
package app.uma.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.uma.dao.entity.Permission;
import app.uma.dao.entity.UserUma;
import app.uma.dao.repository.PermissionRepository;
import app.uma.dao.repository.UserUmaRepository;
import app.uma.web.AppGrantedAuthority;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    UserUmaRepository userRepo;
    @Autowired
    PermissionRepository permissionRepo;

    public UserDetails loadUserByUsername(String username) {
        UserUma user = userRepo.findByUsername(username);
        if (user != null) {
        	Iterable<Permission> permissions = permissionRepo.findByUserId(user.getId());
        	ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        	if(permissions != null){
                for (Permission permission : permissions) {
                    if (permission != null && permission.getName() != null) {

                        GrantedAuthority grantedAuthority = new AppGrantedAuthority(permission.getUrl(), permission.getMethod());
                        grantedAuthorities.add(grantedAuthority);
                    }
                }
        	}
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}