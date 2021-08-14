package com.smrc.api.users.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smrc.api.users.data.UserInfoEntity;
import com.smrc.api.users.data.UserInfoRepository;
//import com.netflix.zuul.context.RequestContext;
import com.smrc.api.users.service.UserService;
import com.smrc.api.users.shared.UserDTO;
import com.smrc.api.users.shared.UserInfoDTO;
import com.smrc.api.users.ui.model.LoginRequestModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	private UserService userService;
	private Environment env;
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	public AuthenticationFilter(UserService userService, Environment env, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.env = env;
        super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, 
			HttpServletResponse res) throws AuthenticationException
	{
		LoginRequestModel creds = null;
		try {
			creds = new ObjectMapper()
					.readValue(req.getInputStream(), LoginRequestModel.class);
			
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getAuthenticationManager().
				authenticate(new UsernamePasswordAuthenticationToken
						(creds.getWinUserID() ,creds.getPassword(), new ArrayList())
						);
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			FilterChain chain, Authentication auth) throws IOException, ServletException
	{
		String userName=  ((User)auth.getPrincipal()).getUsername();
		//UserDTO userDto =userService.getUserDetailsByEmail(userName);
		
		UserInfoDTO userInfoDTO = userService.getUserDetailsByWinID(userName);
		userInfoDTO.setWinUserID(userName);
		
//		RequestContext currentContext = RequestContext.getCurrentContext();
		
		Claims cliClaims  = Jwts.claims().setSubject(userInfoDTO.getWinUserID());
		
		Map<String,Object> userMap=new HashMap();
		userMap.put("id", userInfoDTO.getId());
		cliClaims.put("userInfo", userMap);
//		cliClaims.put
		req.setAttribute("userid", userInfoDTO.getId());
		req.setAttribute("username",userInfoDTO.getDisplayName() );
		String token = Jwts.builder()
				.setClaims(cliClaims)
				.setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(env.getProperty("token.expiration"))))
				.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret")).compact();
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("token", token);
			obj.put("userId", userInfoDTO.getWinUserID());
			obj.put("userName", userInfoDTO.getDisplayName());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//res.addHeader("token", token);
		//res.addHeader("userId", userDto.getUserId());
		res.setContentType("application/json");
		res.getWriter().write(obj.toString());
		//chain.doFilter(req, res);
	}

}
	


