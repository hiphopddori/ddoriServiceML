package io.ddori.be.web.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration 
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)


public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	
	@Autowired
	AuthProvider authProvider;
	
	@Autowired
	LoginSuccessHandler loginSuccessHandler;
	
	@Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(this.authProvider);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 로그인 없이도 허용되야하는 경로
		web.ignoring().antMatchers("/public/**", "/lib/**","/dist/**","/index/**");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests() 
			.antMatchers("/api/admin/**").hasRole("ADMIN") 
			//.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
			.antMatchers("/api/user/login").permitAll()
			.antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
			.anyRequest().authenticated() 
			.and()
			.formLogin()
			//.loginPage("/index/login.html").permitAll()
  			//.usernameParameter("loginId")
  			//.passwordParameter("password")
			//.defaultSuccessUrl("/")
	        //.failureHandler(authFailureHandler)
	        //.successHandler(loginSuccessHandler)
			.and()
  			.logout()
  			//.logoutSuccessUrl("/index/login.html")
  			.deleteCookies("JSESSIONID", "SESSION")
  			.invalidateHttpSession(true)
			.and() 
			.csrf().disable()
			.httpBasic();		
	}
	
}
