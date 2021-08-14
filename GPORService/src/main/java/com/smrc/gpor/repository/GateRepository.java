package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.Gate;
import com.smrc.gpor.model.PipAction;
import com.smrc.gpor.model.ProjectGateDetail;

@Repository
public interface GateRepository extends JpaRepository<Gate, Integer>{

	@Query("select a from ProjectGateDetail  a where a.projectId=:projectId and (a.reportedMonth=:month and isFreezed IS NOT NULL) ")
	public List<ProjectGateDetail> getGateDetails(@Param ("projectId") Integer projectId,@Param ("month") String month );
}
