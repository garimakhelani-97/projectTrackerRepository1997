/*
 * package com.smrc.api.users.data;
 * 
 * import java.util.List;
 * 
 * import org.springframework.data.jpa.repository.Query; import
 * org.springframework.data.repository.CrudRepository;
 * 
 * public interface UserModuleAccessRepository extends
 * CrudRepository<UserModuleAccessEntity, Long> {
 * 
 * 
 * @Query("SELECT uac FROM UserModuleAccessEntity as uac WHERE uac.userInfoId = ?1"
 * ) List<UserModuleAccessEntity> findByuserInfoId(Integer userInfoId);
 * 
 * }
 */