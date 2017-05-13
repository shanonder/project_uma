package app.uma.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import app.uma.web.service.AppUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("userDetailsService")
	AppUserService userDetailsService;
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}
	
	@Bean
	@Qualifier("userDetailsService")
	public AppUserService service(){
		return new AppUserService();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/*","/auth/**","/assets/**","/webjars/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
//		.and().logout().logoutUrl("/login").permitAll()
//		.and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
//		.and().logout().logoutSuccessUrl("/login").permitAll()
		.and().rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400)
		.and().csrf().disable();
//		http.antMatcher("login.html").csrf().disable();
//		  CsrfTokenResponseHeaderBindingFilter csrfTokenFilter = new CsrfTokenResponseHeaderBindingFilter();
//		    CustomAccessDeniedHandler accessDeniedHandler=new CustomAccessDeniedHandler();
//		    http.addFilterAfter(csrfTokenFilter,CsrfFilter.class);
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers( "/assets/**","/webjars/**"); 
	}

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
		.userDetailsService(userDetailsService);
//		.inMemoryAuthentication()
//		.withUser("user").password("password").roles("USER");
	}
}
