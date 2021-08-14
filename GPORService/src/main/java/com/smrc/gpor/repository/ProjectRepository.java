package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.Project;
import com.smrc.gpor.model.UserRoleAssociation;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	/*
	 * @Query("select p.id, p.projectId, p.name as projectName, p.sopMonth, p.sopYear, p.projectLife, p.awardDate,p.recordStatusID,"
	 * +
	 * "cg.name as customerGroupName, c.name as customerName,s.description as status"
	 * +" from project p " +
	 * " inner join  customergroup cg on p.customerGroupId = cg.id " +
	 * "inner join  customer c on c.id = p.customerId " +
	 * "inner join  status s on s.id = p.projectStatusId", nativeQuery = true)
	 * 
	 * List<Object> findAllProject();
	 */
	@Query("select a from Project a where a.projectRefId=:projectRefId ")
	public Project findByprojectRefId(@Param ("projectRefId") String projectRefId);

    @Query("select a.customerGroupId from Project a  where a.id=:id")
	public Integer findCustomerGroupIdById(@Param ("id")Integer projectId) ;
    
    @Query("select a.name from Project a where a.id=:projectId ")
	public String findByprojectId(@Param ("projectId") Integer projectId);
    
    @Query("select a from UserRoleAssociation  a  where a.userId=:userId")
    public List<UserRoleAssociation> getRoles(@Param ("userId") Integer userId);

}
