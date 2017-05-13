package app.uma.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.qq.open.OpenApiV3;

import app.third.Const;
import app.uma.util.MD5Util;
import app.uma.web.AppFilterSecurityInterceptor;
import app.uma.web.CustomUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/guest/**","/auth/**").permitAll()
			.anyRequest().authenticated()
//		.and().formLogin()
//			.loginPage("/guest/login").permitAll()
//		.and().logout().logoutUrl("/guest/login").permitAll()
//		.and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/guest/login"))
//		.and().logout().logoutSuccessUrl("/guest/login").permitAll()
		.and().csrf()
			.disable();
//		http.antMatcher("login").csrf().disable();
		http.addFilterBefore(appFilterSecurityInterceptor, FilterSecurityInterceptor.class);
	}

	//	private Filter ssoFilter() {
	//		CompositeFilter filter = new CompositeFilter();
	//		List<Filter> filters = new ArrayList<>();
	////		filters.add(ssoFilter(facebook(), "/login/facebook"));
	////		filters.add(ssoFilter(github(), "/login/github"));
	//		filters.add(tencentFilter("/login/tencent"));
	//		filter.setFilters(filters);
	//		return filter;
	//	}

	@Bean
	public OpenApiV3 openApi(){
		return new OpenApiV3(Const.appId, Const.appkey);
	}

	//
	//	private Filter tencentFilter(String path) {
	//		
	//		return null;
	//	}

	@Autowired
	private AppFilterSecurityInterceptor appFilterSecurityInterceptor;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers( "/assets/**","/webjars/**"); 
	}

	@Bean
	UserDetailsService customUserService(){ //注册UserDetailsService 的bean
		return new CustomUserService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder(){

			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String)rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(MD5Util.encode((String)rawPassword));
			}}); //user Details Service验证

	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}
