package com.smrc.mdm.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smrc.mdm.model.CustomProjectProcessAssociation;
import com.smrc.mdm.model.ProjectProcessAssociation;

@Repository
@Transactional
public interface ProjectProcessAssociationRepository extends JpaRepository<ProjectProcessAssociation, Integer> {

	List<ProjectProcessAssociation> findByProcessId(Integer processId);

	List<ProjectProcessAssociation> findByProjectId(Integer id);
	
     ProjectProcessAssociation findByProjectIdAndProcessId(Integer projectId, Integer processId);
     
     public void deleteByProjectId(Integer projectId);
     
     @Query("SELECT new com.smrc.mdm.model.CustomProjectProcessAssociation( p.project.id, p.process.name, p.startDate, p.endDate) "
 			+ "FROM ProjectProcessAssociation AS p ORDER BY p.id DESC")
     List<CustomProjectProcessAssociation> findAllCustomProjectProcessAssociation();

}
