///**
// *
// * author: shanonder
// * date: 2017年4月30日 上午1:07:05
// *
// */
//package app.uma.net;
//
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class AutoProvisioningUserDetailsService implements AuthenticationUserDetailsService;
//OpenIDAuthenticationToken
//{
//	public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
//		return new User(token.getName(), "NOTUSED", AuthorityUtils.createAuthorityList("ROLE_USER"));
//	}
//}
