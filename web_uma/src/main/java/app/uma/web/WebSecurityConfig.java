package app.uma.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

import com.qq.open.OpenApiV3;

import app.third.Const;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.antMatcher("/**")
		.authorizeRequests()
		.antMatchers("/auth/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login.html")
		.permitAll()
			.and().logout()
		.logoutUrl("/login.html")
		.permitAll()
			.and().exceptionHandling()
		.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
			.and().logout()
		.logoutSuccessUrl("/").permitAll().
			and().csrf()
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
		.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}
	
	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
//		filters.add(ssoFilter(facebook(), "/login/facebook"));
//		filters.add(ssoFilter(github(), "/login/github"));
		filters.add(tencentFilter("/login/tencent"));
		filter.setFilters(filters);
		return filter;
	}
	@Bean
	private OpenApiV3 openApi(){
		return new OpenApiV3(Const.appId, Const.appkey);
	}
	

	private Filter tencentFilter(String path) {
		
		return null;
	}



	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/", "/assets/**","/webjars/**"); 
	}

//	@Override
//	public void 
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
//		.userDetailsService(UserDetailsService.class)
		.inMemoryAuthentication()
		.withUser("user").password("password").roles("USER");
	}
}
