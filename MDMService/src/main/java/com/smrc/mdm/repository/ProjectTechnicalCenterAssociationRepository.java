package com.smrc.mdm.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smrc.mdm.model.CustomProjectProcessAssociation;
import com.smrc.mdm.model.CustomProjectTechnicalCenterAssociation;
import com.smrc.mdm.model.ProjectTechnicalCenterAssociation;
@Repository
@Transactional
public interface ProjectTechnicalCenterAssociationRepository extends JpaRepository<ProjectTechnicalCenterAssociation, Integer>{
	
	public void deleteByProjectId(Integer projectId);
	
	List<ProjectTechnicalCenterAssociation> findByProjectId(Integer projectId);
	
	@Query("SELECT new com.smrc.mdm.model.CustomProjectTechnicalCenterAssociation( p.projectId, p.unit.description ) "
 			+ "FROM ProjectTechnicalCenterAssociation AS p ORDER BY p.id DESC") 
     List<CustomProjectTechnicalCenterAssociation> findAllCustomProjectTechnicalCenterAssociation();

}
