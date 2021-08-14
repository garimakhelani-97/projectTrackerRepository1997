package com.smrc.api.users.ui.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.api.users.data.MenuEntity;
import com.smrc.api.users.data.RoleEntity;
import com.smrc.api.users.data.UserInfoEntity;
import com.smrc.api.users.data.UserInfoRepository;
import com.smrc.api.users.data.UserRoleRepository;
import com.smrc.api.users.service.UserService;
import com.smrc.api.users.shared.UserInfoDTO;
import com.smrc.api.users.ui.model.MenuModel;
import com.smrc.api.users.ui.model.SubMenuModel;
import com.smrc.api.users.ui.model.SubMenuModel1;
import com.smrc.api.users.ui.model.UserInfoRequestModel;
import com.smrc.api.users.ui.model.UserResponseModel;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@GetMapping("/status/check")
	public ResponseEntity<String> status() {
		JSONObject obj = new JSONObject();
		String s = userService.getUserRoles();
		try {
			obj.put("role", s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(obj.toString());
	}

	/*
	 * @GetMapping("/roles") public ResponseEntity<?>
	 * getUserRoles(@RequestParam("userId") Long userId) {
	 * L̥
	 * //return new ResponseEntity<>(userService.getUserRoles(userId),
	 * HttpStatus.OK);
	 * 
	 * return null; }
	 */
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody UserInfoRequestModel userInfoReqModel) {
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserInfoDTO userInfoDto = modelmapper.map(userInfoReqModel, UserInfoDTO.class);
		UserInfoEntity createdUers = userService.createUser(userInfoDto);
		return new ResponseEntity<>(createdUers, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/modules") public ResponseEntity<?>
	 * getUserModules(@RequestParam("userId") Integer userId) {
	 * 
	 * return new ResponseEntity<>(userService.getModulesByUserId(userId),
	 * HttpStatus.OK); }
	 */

	@GetMapping("/roles")
	public ResponseEntity<?> getUserRoles(@RequestParam("winUserID") String winUserID) {
		UserInfoEntity userInfoEntity = userInfoRepository.findByWindowUserId(winUserID);
		Map<String, Object> obj = new HashMap<String, Object>();

		List<String> rolelist = new ArrayList();
		Set<RoleEntity> roleEntities = userInfoEntity.getUserRoles();

		for (RoleEntity roleEntity : roleEntities) {

			rolelist.add(roleEntity.getName());

		}

		obj.put("roles", rolelist);
		obj.put("userId", userInfoEntity.getId());

		return new ResponseEntity<>(obj, HttpStatus.OK);

	}

	@GetMapping("/menus")
	public ResponseEntity<?> getUserMenus(@RequestParam("winUserID") String winUserID) {

		UserInfoEntity userInfoEntity = userInfoRepository.findByWindowUserId(winUserID);
		List<String> rolelist = new ArrayList();
		List<MenuModel> menulist = new ArrayList<MenuModel>();
		
		List<MenuModel> menulst = new ArrayList<MenuModel>();

		Set<SubMenuModel> subMenulist = null;

		Map<String, Object> obj = new HashMap<String, Object>();

		Set<RoleEntity> roleEntities = userInfoEntity.getUserRoles();

		for (RoleEntity roleEntity : roleEntities) {

			rolelist.add(roleEntity.getName());

			for (MenuEntity menuEntity : roleEntity.getUserMenus()) {

				subMenulist = new HashSet<SubMenuModel>();

				for (MenuEntity me : roleEntity.getUserMenus()) {
					if ((menuEntity.getId() == me.getPid()) && !(menuEntity.equals(me))) {
						subMenulist.add(new SubMenuModel(me.getName(), me.getUri(), me.getType(), me.getMenuKey(),
								me.getPid(), me.getOrderId()));
					}
				}
				
				//subMenulist.sort(Comparator.comparing(MenuEntity::getOrderId));
				if (menuEntity.getPid() == 0) {
					menulist.add(new MenuModel(menuEntity.getName(), menuEntity.getUri(), menuEntity.getType(),
							menuEntity.getMenuKey(), menuEntity.getPid(), menuEntity.getOrderId(),
							menuEntity.getDescription(), subMenulist));
				}
			}

		}

		if(!menulist.isEmpty()) {			
			menulst = (ArrayList<MenuModel>)menulist.stream().collect(Collectors.toList());
			Collections.sort(menulst);
		}
		// obj.put("roles",rolelist);
		System.out.println("menulist--------------------------------------------------------" + menulist);

		obj.put("menu", menulst);

		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

}
