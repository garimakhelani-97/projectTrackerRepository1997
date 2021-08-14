package com.smrc.gateone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.model.Gate1ProjectProcessAssociation;

@Repository
public interface Gate1ProjectProcessAssociationRepository extends JpaRepository<Gate1ProjectProcessAssociation, Integer> {
	
	@Query(" select a from Gate1ProjectProcessAssociation a where a.projectId=:projectId and a.processId=:processId ")
	public Gate1ProjectProcessAssociation findByprojectIdprocessId(@Param ("projectId") Integer projectId,@Param ("processId") Integer processId  );

	@Query(" select a from Gate1ProjectProcessAssociation a where a.projectId=:projectId and a.processId  is null ")
	public Gate1ProjectProcessAssociation findByprojectIdprocessIdNull(@Param ("projectId") Integer projectId );

	
	@Query("select projectProcessAssociation from Gate1ProjectProcessAssociation as projectProcessAssociation where projectProcessAssociation.projectId IN(:projectIds)")
	public List<Gate1ProjectProcessAssociation> findAllByprojectId(@Param("projectIds")List<Integer> projectIds);

	public List<Gate1ProjectProcessAssociation> findAllByProjectIdOrderByIdDesc(Integer projectId);
	
	@Query("select a from Gate1ProjectProcessAssociation a where  a.projectId=:projectId and a.processId=1 ")
	public Gate1ProjectProcessAssociation findGPORProcessExistence(@Param ("projectId") Integer projectId );
	

}
