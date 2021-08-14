package com.smrc.gateone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.dto.ProjectListDTO;
import com.smrc.gateone.model.Project;

//import com.smrc.gpor.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	@Query("select a from Project a where a.parentProjectId is null order by a.updatedDate desc  ")
	public List<Project>  getProjectsViaMonth();

	@Query("SELECT new com.smrc.gateone.dto.ProjectListDTO(p.id, p.projectRefId, p.name, p.unitId, p.unitName,p.unitDescription,p.updatedDate,p.currenyId,p.currencyName,p.sopMonth,p.sopYear,p.recordStatusID) "
			+ "FROM Project AS p ORDER BY p.id DESC")
	public List<ProjectListDTO> findAllProjectListDTO();
	
	public Project findByprojectRefId(String projectId);
	
	@Query("select a.customerGroupId from Project a  where a.id=:id")
	public Integer findCustomerGroupIdById(@Param ("id")Integer projectId) ;
}
