package com.smrc.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.smrc.api.users.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private Environment environment;
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public WebSecurity(Environment environment,UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		this.environment = environment;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity )throws Exception
	{
		//httpSecurity.cors();
		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests().antMatchers("/users/**").permitAll()
		.and()
		.addFilter(getAuthenticationFilter())
		;
	}
	
	private AuthenticationFilter getAuthenticationFilter() throws Exception
	{
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, environment, authenticationManager());
		//authenticationFilter.setAuthenticationManager(authenticationManager());
		return authenticationFilter;
	}
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth)throws Exception
	{
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	
	/*
	 * @Bean public FilterRegistrationBean corsFilter() {
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); CorsConfiguration config = new
	 * CorsConfiguration(); config.setAllowCredentials(true);
	 * config.addAllowedOrigin("http://172.29.37.247:4200");
	 * config.addAllowedHeader("*"); config.addAllowedMethod("*");
	 * source.registerCorsConfiguration("/**", config); FilterRegistrationBean bean
	 * = new FilterRegistrationBean(new CorsFilter(source)); bean.setOrder(0);
	 * return bean; }
	 */

	
}
