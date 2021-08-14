package com.smrc.api.users.service;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smrc.api.users.data.UserInfoEntity;
import com.smrc.api.users.data.UserInfoRepository;
import com.smrc.api.users.shared.UserInfoDTO;

@Service
public class UserServiceImpl implements UserService {
	
	//UserRepository userRepository;
	UserInfoRepository userInfoRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	RoleService roleService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * @Autowired public UserServiceImpl(UserRepository userRepository,
	 * BCryptPasswordEncoder bCryptPasswordEncoder) { this.userRepository =
	 * userRepository; this.bCryptPasswordEncoder = bCryptPasswordEncoder; }
	 */

	
	@Autowired
	public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
			UserInfoRepository userInfoRepository)
	{
		
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userInfoRepository = userInfoRepository;
		
	}
	/*
	 * @Override public UserDTO createUser(UserDTO userDto) { // TODO Auto-generated
	 * method stub userDto.setUserId(UUID.randomUUID().toString());
	 * userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword
	 * ()));
	 * 
	 * ModelMapper modelmapper = new ModelMapper();
	 * modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
	 * ; UserEntity userEntity = modelmapper.map(userDto, UserEntity.class);
	 * userRepository.save(userEntity);
	 * 
	 * UserDTO userdtoReturnValue = modelmapper.map(userEntity, UserDTO.class);
	 * return userdtoReturnValue; }
	 */

	
	@Override
	public UserInfoEntity createUser(UserInfoDTO userInfoDto) {
		// TODO Auto-generated method stub
		userInfoDto.setWinUserID(userInfoDto.getWinUserID());
		userInfoDto.setPassword(bCryptPasswordEncoder.encode(userInfoDto.getPassword()));
		//userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		
		/*
		 * ModelMapper modelmapper = new ModelMapper();
		 * modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
		 * ;
		 */
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		userInfoEntity.setId(13);
		userInfoEntity.setWindowUserId(userInfoDto.getWinUserID());
		userInfoEntity.setPassword(userInfoDto.getPassword());
	    
		
		UserInfoEntity userInfoEntityReturn = userInfoRepository.save(userInfoEntity);
		return userInfoEntityReturn;
	}
	
	
	
	
	
	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { // TODO Auto-generated method stub
	 * 
	 * UserEntity userEntity = userRepository.findByEmail(username);
	 * 
	 * if(userEntity == null) throw new UsernameNotFoundException(username);
	 * 
	 * return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),
	 * true, true, true, true, new ArrayList<>()); }
	 */
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserInfoEntity userInfoEntity = userInfoRepository.findByWindowUserId(username);
		
		if(userInfoEntity == null) throw new UsernameNotFoundException(username);
		
		return new User(userInfoEntity.getWindowUserId(),userInfoEntity.getPassword(), true, true, true, true, new ArrayList<>());
	}

	/*
	 * @Override public UserDTO getUserDetailsByEmail(String email) { UserEntity
	 * userEntity = userRepository.findByEmail(email);
	 * 
	 * if (userEntity == null) throw new UsernameNotFoundException(email); return
	 * new ModelMapper().map(userEntity, UserDTO.class); }
	 */

	
	@Override
	public UserInfoDTO getUserDetailsByWinID(String winId) {
		UserInfoEntity userInfoEntity = userInfoRepository.findByWindowUserId(winId);

		if (userInfoEntity == null)
			throw new UsernameNotFoundException(winId);
		return new ModelMapper().map(userInfoEntity, UserInfoDTO.class);
	}
	
	@Override
	public String getUserRoles() {
		logger.info("inside User Role service");
		
		roleService.getUserMenuByRole();
		return "User role Service Working";
	}

	/*
	 * @Override public Map<String, Object> getUserRoles(long id) {
	 * 
	 * UserEntity userEntity = userRepository.findById(id); Map<String, Object> obj
	 * = new HashMap<String, Object>(); Set<RoleEntity> roleEntities =
	 * userEntity.getRoles();
	 * 
	 * List<String> rolelist = new ArrayList(); Set<MenuModel> menulist = new
	 * HashSet<MenuModel>(); Set<SubMenuModel> subMenulist = null; for(RoleEntity
	 * roleEntity : roleEntities) { rolelist.add(roleEntity.getRoleName());
	 * 
	 * for(MenuEntity menuEntity : roleEntity.getMenus()) { subMenulist =new
	 * HashSet<SubMenuModel>(); for(SubMenuEntity subMenuEntity:
	 * menuEntity.getSubMenu()) { subMenulist.add(new
	 * SubMenuModel(subMenuEntity.getSubMenuName(), subMenuEntity.getUri(),
	 * subMenuEntity.getSubMenuType(), subMenuEntity.getOrderId())); }
	 * 
	 * menulist.add(new MenuModel(menuEntity.getMenuName(), menuEntity.getUri(),
	 * menuEntity.getMenuType(), menuEntity.getMenuKey(),
	 * menuEntity.getOrderId(),subMenulist)); }
	 * 
	 * } obj.put("menu", menulist); obj.put("roles",rolelist); return obj; }
	 */


	/*
	 * @Override public List<ModuleDTO> getModulesByUserId(int userInfoId) {
	 * 
	 * List<UserModuleAccessEntity> userModuleAccessEntity =
	 * userModuleAccessRepository.findByuserInfoId(userInfoId);
	 * 
	 * List<ModuleDTO> moduleList = new ArrayList<>();
	 * 
	 * for(UserModuleAccessEntity userModuleObj : userModuleAccessEntity) {
	 * 
	 * ModuleEntity moduleEntity =
	 * moduleRepository.findBymoduleId(userModuleObj.getModuleEntity().getModuleId()
	 * ); moduleList.add(new
	 * ModuleDTO(moduleEntity.getModuleId(),moduleEntity.getModuleCode(),
	 * moduleEntity.getModuleName(), moduleEntity.getModuleDesc())); }
	 * 
	 * return moduleList; }
	 */

}
