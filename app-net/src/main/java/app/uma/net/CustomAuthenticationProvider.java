/**
 *
 * author: shanonder
 * date: 2017年4月30日 上午1:12:31
 *
 */
package app.uma.net;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component 
public class CustomAuthenticationProvider implements AuthenticationProvider {
	   
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
   
        // use the credentials to try to authenticate against the third party system         
        if (authenticatedAgainstThirdPartySystem()) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
        } else {
            return null;
        }
    }
   
    private boolean authenticatedAgainstThirdPartySystem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
   
}