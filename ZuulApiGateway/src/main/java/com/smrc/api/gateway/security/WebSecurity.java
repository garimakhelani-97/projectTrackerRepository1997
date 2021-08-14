package com.smrc.api.gateway.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final Environment environment;

	@Autowired
	public WebSecurity(Environment environment) {
		this.environment = environment;

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* http.addFilter(new CORSFilter()); */

		http.csrf().disable();
		// http.headers().frameOptions().disable();
		http.cors().and().authorizeRequests().antMatchers("/users-ws/users").permitAll().antMatchers("/users-ws/login")
//				.permitAll().antMatchers("/gpor-ws/sync/addProject")
//				.permitAll().antMatchers("/gpor-ws/sync/updateProject")
				.permitAll().antMatchers("/gateone-ws/sync/addProject")
				.permitAll().antMatchers("/gpor-ws/sync/addProject")
				.permitAll().antMatchers("/gpor-ws/sync/updateProject")
				.permitAll().antMatchers("/users-ws/sso/generateToken")
				//.permitAll().antMatchers("/users-ws/sso/login")
				.permitAll().antMatchers("/users-ws/ssoAuth/sso/authentication")
				.permitAll().antMatchers("/gpor-ws/sync/addProjectProcessAndTransactionsData")
				.permitAll().antMatchers("/gpor-ws/sync/updateProjectWhenItsGPOR").permitAll()
				.anyRequest().authenticated().and()
				.addFilter(new AuthorizationFilter(authenticationManager(), environment));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
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
	@Bean
	public ZuulLoggingFilter zuulLoggingFilter() {
		return new ZuulLoggingFilter();
	}
}
