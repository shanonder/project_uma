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
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.qq.open.OpenApiV3;

import app.third.Const;
import app.uma.web.MyFilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/","/auth/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login.html").permitAll()
		.and().logout().logoutUrl("/login.html").permitAll()
		.and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
		.and().logout().logoutSuccessUrl("/login.html").permitAll()
		.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//		.and()
//		.addFilterBefore(springSecurityFilterChain, BasicAuthenticationFilter.class);
		http.antMatcher("login.html").csrf().disable();
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

//	@Autowired
//    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers( "/assets/**","/webjars/**"); 
	}

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
    
    @Autowired
    UserDetailsService customUserService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService); //user Details Service验证

    }
}
