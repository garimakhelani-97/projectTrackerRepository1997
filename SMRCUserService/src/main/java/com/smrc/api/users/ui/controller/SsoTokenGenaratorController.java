package com.smrc.api.users.ui.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smrc.api.users.data.UserAppAccessLog;
import com.smrc.api.users.data.UserAppAccessLogRepository;
//import com.smrc.api.users.security.SsoLoginService;
import com.smrc.api.users.utils.IdEncoder;

@RestController
@RequestMapping("/sso")
public class SsoTokenGenaratorController {

	public static String chkDirect;
	public static String statusdetails;
	public int errorcode;

	@Autowired
	UserAppAccessLogRepository userAppAccessLogRepository;

	@GetMapping("/generateToken")
	public String ssoTokenGenerate(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, IOException, JSONException {

		String winUserId = request.getParameter("winUserId");
		String domain = request.getParameter("domain");
		String sessionId = request.getParameter("sessionId");
		String callback = request.getParameter("callback");

		chkDirect = "M1";
		 //statusdetails = SsoLoginService.getstaticdetails();
		// errorcode=SsoLoginService.geterrorcode();

		String frmApp = request.getParameter("frmApp");
		if (StringUtils.isEmpty(sessionId)) {
			sessionId = "12345678";
		}

		String landingpage = request.getParameter("landingpage");

		UUID uuid = UUID.randomUUID();

		JSONObject obj = new JSONObject();

		if (StringUtils.isBlank(landingpage)) {
			obj.put("winUserId", IdEncoder.encodeId(winUserId, uuid.toString()));
			obj.put("domain", IdEncoder.encodeId(domain, uuid.toString()));
			obj.put("sessionId", sessionId);
			obj.put("UUID", uuid.toString());
		} else {
			Base64.Encoder encoder = java.util.Base64.getEncoder();

			String pageEncoded = encoder.encodeToString(landingpage.getBytes(StandardCharsets.UTF_8));

			obj.put("winUserId", IdEncoder.encodeId(winUserId, uuid.toString()));
			obj.put("domain", IdEncoder.encodeId(domain, uuid.toString()));
			obj.put("sessionId", sessionId);
			obj.put("UUID", uuid.toString());
			obj.put("landingpage", pageEncoded);

		}

		UserAppAccessLog userAppAccessLog = new UserAppAccessLog();
		userAppAccessLog.setDomain(domain);
		userAppAccessLog.setWinuserid(winUserId);
		userAppAccessLog.setToken(uuid.toString());
		userAppAccessLog.setCreatedOn(new Date());
		userAppAccessLog.setAccessdate(new Date());
		userAppAccessLog.setConnectedto(chkDirect);
		//userAppAccessLog.setStatus(statusdetails);
		//userAppAccessLog.setErrorcode(errorcode);
		userAppAccessLogRepository.save(userAppAccessLog);

		if (!StringUtils.isBlank(callback)) {
			return callback + "(" + obj.toString() + ")";
		} else {
			return obj.toString();
		}

	}

}
