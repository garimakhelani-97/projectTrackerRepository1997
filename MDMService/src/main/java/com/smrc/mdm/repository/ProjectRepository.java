package com.smrc.mdm.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smrc.mdm.model.Project;
import com.smrc.mdm.model.ProjectListMapper;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	List<Project> findByRecordStatusID(Integer recordStatusID);
	
	Project findTopByOrderByIdDesc();

	@Query("SELECT new com.smrc.mdm.model.ProjectListMapper(p.id, p.projectId, p.name, p.sopMonth, p.sopYear,p.projectLife,p.awardDate,p.recordStatusID,p.customerGroup.name,p.customer.name,p.unit.description,p.brand.name,p.status.description,p.updatedDate) "
			+ "FROM Project AS p ORDER BY p.id DESC")//
	List<ProjectListMapper> findAllProjectListMapper();
}