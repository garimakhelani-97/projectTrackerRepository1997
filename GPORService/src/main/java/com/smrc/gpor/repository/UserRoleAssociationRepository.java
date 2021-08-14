package com.smrc.gpor.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.UserRoleAssociation;

@Repository
public interface UserRoleAssociationRepository extends JpaRepository<UserRoleAssociation, Integer> {

	List<UserRoleAssociation> findByRoleId(Integer userId);

	List<UserRoleAssociation> findAllByUserId(Integer approverIds);

	//List<UserRoleAssociation> findAllByUserIdIn(List<Integer> userIdList,List<Integer> userRoleIdList);

	List<UserRoleAssociation> findAllByUserIdInAndRoleIdIn(List<Integer> userIdList, List<Integer> userRoleIdList);

	List<UserRoleAssociation> findAllByUserIdIn(List<Integer> approverIds);
	
	
@Query("select max(a.id) as id, a.userEmail as userEmail from UserRoleAssociation as a where a.userId=:userId")
 		Map getEmailId(Integer userId);

	@Query("select a.userId from UserRoleAssociation a where a.id=:id")
	Integer findUserIdById(@Param("id")Integer projectControllerTableId);

	UserRoleAssociation findByUserId(Integer projetSubmitterId);
	
	@Query("select a.id from UserRoleAssociation a where a.userId=:userId")
	Integer findIdByUserId(@Param("userId")Integer userId);
	
	@Query("select a.userName from UserRoleAssociation a where a.userId=:userId")
	String getUserName(@Param("userId")Integer userId);
	
List<UserRoleAssociation> findAllByIdIn(List<Integer> submitterPKId);
	

	@Query("select a.userId from UserRoleAssociation a where a.id IN(:id)")
	List<Integer> findUserIdByIdIn(@Param("id")List<Integer> submitterPKId);

}
