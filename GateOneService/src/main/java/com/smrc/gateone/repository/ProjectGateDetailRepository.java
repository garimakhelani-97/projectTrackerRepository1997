package com.smrc.gateone.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.model.ProjectGateDetails;

@Repository
public interface ProjectGateDetailRepository extends JpaRepository<ProjectGateDetails, Integer> {
	List<ProjectGateDetails> findByprojectId(Integer id);
	
	@Query(" select a.projectId as projectId,a.gateId as gateId,a.initialDate as initialDate,a.statusId as statusId"
			+ " ,a.isFreezed as isFreezed  from ProjectGateDetails a  where a.projectId=:projectId")
	List<Map> findDatByProjectId(@Param("projectId") Integer projectId);
	
	@Query(" select a from ProjectGateDetails a where a.projectId=:projectId and a.gateId=:gateId ")
	ProjectGateDetails findByProjectIdandGateId(@Param("projectId") Integer projectId,@Param("gateId") Integer gateId);
}
