package com.smrc.api.gateway.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//import com.smrc.api.users.shared.UserInfoDTO;

//import com.smrc.api.users.shared.UserInfoDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	Environment environment;
	public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
		
		super(authenticationManager);
		this.environment = environment;
	}
	
	 @Override
	 protected void doFilterInternal(HttpServletRequest req,
	                                    HttpServletResponse res,
	                                    FilterChain chain) throws IOException, ServletException {
	 String authorizationHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));
	 if (authorizationHeader == null || !authorizationHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))) {
         chain.doFilter(req, res);
         return;
     }
	 UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

     SecurityContextHolder.getContext().setAuthentication(authentication);
     chain.doFilter(req, res);
	 }
	 
	 
	 private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
         
         logger.info("User Pssword Authentication");
         String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));
            if (authorizationHeader == null) {
                return null;
            }
            String token = authorizationHeader.replace(environment.getProperty("authorization.token.header.prefix"), "");
            String secret = environment.getProperty("token.secret");
           
            String userId = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
           
            if(userId == null)
            {
                return null;
            }
//            UserInfoDTO userInfoDTO = userService.getUserDetailsByWinID(userName);
            request.getAttribute("userId");
            return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
        }
	

}
