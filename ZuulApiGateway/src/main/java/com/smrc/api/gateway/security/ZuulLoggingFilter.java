package com.smrc.api.gateway.security;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

//import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
//import com.smrc.api.users.shared.UserInfoDTO;
//import com.smrc.api.users.shared.UserInfoDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

//@Component
public class ZuulLoggingFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);
	@Autowired
	private Environment env;
	
	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
	    HttpServletRequest request = currentContext.getRequest();
		logger.info("request -> {} request uri -> {} request header -> {}", request, request.getRequestURI());
		logger.info("Inside zuul filter....");
		String remoteAddr = request.getRemoteAddr();
		String url = currentContext.getRequest().getRequestURL().toString();
		logger.debug("URL"+ url);

		
		logger.info("host " + request.getRemoteHost());
		logger.info("port " + String.valueOf(request.getLocalPort()));
		currentContext.getZuulRequestHeaders().put(RequestDispatcher.FORWARD_REQUEST_URI, remoteAddr);
		String userid="";
		if(request.getAttribute("userid")!=null)
			{userid=request.getAttribute("userid").toString();}
//		String userName=  ((User)auth.getPrincipal()).getUsername();
		//UserDTO userDto =userService.getUserDetailsByEmail(userName);
		
//		UserInfoDTO userInfoDTO = userService.getUserDetailsByWinID(userName);
//		userInfoDTO.setWinUserID(userName);
		
//		Claims cliClaims  = Jwts.claims().setSubject(userInfoDTO.getWinUserID());
//		Map<String,Object> userMap=new HashMap();
//		userMap.put("id", userInfoDTO.getId());

		
		
		
		 String authorizationHeader = request.getHeader(env.getProperty("authorization.token.header.name"));
         if (authorizationHeader == null) {
             return null;
         }
         String token = authorizationHeader.replace(env.getProperty("authorization.token.header.prefix"), "");
         String secret = env.getProperty("token.secret");
        
         Claims body = Jwts.parser()
                 .setSigningKey(secret)
                 .parseClaimsJws(token).getBody();
         
         Map map =   (Map) body.get("userInfo");
         
         String userId=map.get("id").toString();
//         System.out.println("Map "   + map.get("id").getClass());
         
			/*
			 * String userId = Jwts.parser() .setSigningKey(secret) .parseClaimsJws(token)
			 * .getBody() .getSubject();
			 */
         
         currentContext.getZuulRequestHeaders().put("userid",userId.toString());
		
//		currentContext.getZuulRequestHeaders().put("daart_app_port", "8080");
		Enumeration<String> headerNames = request.getHeaderNames();
		request.setAttribute("name", "saurabh");
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                logger.info("Name : " + name);
                String values = request.getHeader(name);
                logger.info("Values : " + values);
                currentContext.addZuulRequestHeader(name, values);
            }
        }
		return null;
	}

		@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}


}
