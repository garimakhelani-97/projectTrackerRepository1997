package com.smrc.api.users.ui.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.api.users.data.UserInfoEntity;
import com.smrc.api.users.data.UserInfoRepository;
import com.smrc.api.users.utils.IdEncoder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/ssoAuth")
public class SsoLoginController {

	@Autowired
	UserInfoRepository userInfoRepository;

	@Value("${token.expiration}")
	private String jwtExpirationInMs;

	@Value("${token.secret}")
	private String jwtSecret;

	@GetMapping("sso/authentication")
	public void ssoAuthentication(@RequestParam String winUserId, @RequestParam String domain,@RequestParam String UUID, HttpServletResponse res)
			throws IOException {
		
		String decWinUserId  = IdEncoder.decodeId(winUserId,UUID);
		String decDomain	=	IdEncoder.decodeId(domain,UUID);

		UserInfoEntity userInfoEntity = userInfoRepository.findByWindowUserIdAndDomainName(decWinUserId,decDomain);

		if (userInfoEntity == null) {
			throw new BadCredentialsException("User does not exist.");
		}
		
        Claims cliClaims  = Jwts.claims().setSubject(userInfoEntity.getWindowUserId());
		
		Map<String,Object> userMap=new HashMap();
		userMap.put("id", userInfoEntity.getId());
		cliClaims.put("userInfo", userMap);

		String token = Jwts.builder()
				.setClaims(cliClaims)
				.setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(jwtExpirationInMs)))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
//		String jwt = Jwts.builder().setSubject(userInfoEntity.getWindowUserId())
//				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpirationInMs)))
//				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("token", token);
			obj.put("userId", userInfoEntity.getWindowUserId());
			obj.put("userName", userInfoEntity.getDisplayName());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		res.setContentType("application/json");
		res.getWriter().write(obj.toString());
	}
}

//try {
//    // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
//    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//    token.setDetails(new WebAuthenticationDetails(request));
//    Authentication authentication = this.authenticationProvider.authenticate(token);
//    logger.debug("Logging in with [{}]", authentication.getPrincipal());
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//} catch (Exception e) {
//    SecurityContextHolder.getContext().setAuthentication(null);
//    logger.error("Failure in autoLogin", e);
//}