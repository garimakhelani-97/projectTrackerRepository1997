package com.smrc.api.users.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.smrc.api.users.data.UserInfoEntity;
import com.smrc.api.users.shared.ModuleDTO;
import com.smrc.api.users.shared.UserDTO;
import com.smrc.api.users.shared.UserInfoDTO;

public interface UserService extends UserDetailsService {

	//UserDTO createUser(UserDTO userDto);
	//UserDTO getUserDetailsByEmail(String email);
	String getUserRoles();
	
	//Map<String, Object> getUserRoles(long id);
	
	UserInfoDTO getUserDetailsByWinID(String winId);
	UserInfoEntity createUser(UserInfoDTO userInfoDto);
	
	//List<ModuleDTO> getModulesByUserId(int userInfoId);
	
}
