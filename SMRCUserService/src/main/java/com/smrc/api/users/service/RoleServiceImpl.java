package com.smrc.api.users.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	@Override
	public String getUserMenuByRole() {
		logger.info("Role Service");
		return "Role Menu Service";
	}
	

}
