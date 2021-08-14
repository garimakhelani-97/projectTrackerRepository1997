package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.ProjectProcessAssociation;

@Repository
public interface ProjectProcessAssociationRepository extends JpaRepository<ProjectProcessAssociation, Integer> {

	List<ProjectProcessAssociation> findByProcessId(Integer processId);

	List<ProjectProcessAssociation> findByProjectId(Integer id);

	@Query("select a from ProjectProcessAssociation a order by a.id desc ")
	public  List<ProjectProcessAssociation> findAllbyIDDesc();

	@Query("select a.projectControllerId from ProjectProcessAssociation a where a.projectId=:projectId")
	Integer findAllIdByProjectId(@Param("projectId")Integer projectId);
	
	@Query("select a.programManagerId from ProjectProcessAssociation a where a.projectId=:projectId")
	Integer findProgramManagerIdByProjectId(@Param("projectId")Integer projectId);
	/*
	 * @Query("select new com.smrc.gpor.model.ProjectProcessAssociation(d.projectControllerId,d.projectManagerId) from ProjectProcessAssociation d where d.projectId=:projectId"
	 * ) ProjectProcessAssociation
	 * findsubmitterIdByProjectId(@Param("projectId")Integer projectId);
	 */
	@Query("select a.projectManagerId from ProjectProcessAssociation a where a.projectId=:projectId")
	Integer findProjectManagerByProjectId(@Param("projectId") Integer projectId);
}
