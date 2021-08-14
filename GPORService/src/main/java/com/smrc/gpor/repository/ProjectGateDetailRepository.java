package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.ProjectGateDetail;

@Repository
public interface ProjectGateDetailRepository extends JpaRepository<ProjectGateDetail, Integer>{

	List<ProjectGateDetail> findByReportedMonth(String reportedMonth);
	
	List<ProjectGateDetail> findByProjectIdAndReportedMonth(Integer projectId,String reportedMonth,Sort sort);
	
	List<ProjectGateDetail> findByProjectIdAndReportedMonthAndIsFreezed(Integer projectId,String reportedMonth,Integer isFreezed,Sort sort);

	@Query("select a from ProjectGateDetail a where a.projectId=:projectId and a.gateId=:gateId and a.reportedMonth=:reportedMonth ")
	ProjectGateDetail findByProjectIdGateIdAndReportedMonth(@Param ("projectId") Integer projectId,@Param ("gateId") Integer gateId ,
															@Param ("reportedMonth") String reportedMonth);
}
