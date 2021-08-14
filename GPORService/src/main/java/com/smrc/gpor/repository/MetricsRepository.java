package com.smrc.gpor.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import com.smrc.gpor.dto.MetricsRensponseDTO;
import com.smrc.gpor.dto.ProjectWithMetricsDTO;
import com.smrc.gpor.model.Metrics;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics, Integer>{
	
//	@Query("select m.id as id,m.name as name, m.description as description, pmd.comment as comment, pmd.statusId as statusId, pmd.id as projectMetricsTableId"
//			+ " from Metrics m left join ProjectMetricsDetail pmd on m.id = pmd.metricsId")
//	
	//List<Map> getMetrics();
	
	@Query("select m.id as id,m.name as name, m.description as description, pmd.comment as comment, pmd.statusId as statusId, pmd.id as projectMetricsTableId"
			+ "  from Metrics m left join ProjectMetricsDetail pmd on m.id = pmd.metricsId AND pmd.projectId=:id AND pmd.reportedMonth =:currentReportedMonth")
	List<Map> getMetricsNameByCurrentReportingMonth(@Param("id") Integer id,@Param("currentReportedMonth") String currentReportedMonth); 
	
	@Query("select m.id as id,m.name as name, m.description as description, pmd.comment as comment, pmd.statusId as statusId"
			+ "  from Metrics m left join ProjectMetricsDetail pmd on m.id = pmd.metricsId AND pmd.projectId=:id AND pmd.reportedMonth =:previousReportingMonth")
	List<Map> getMetricsNameByPreviousReportingMonth(@Param("id") Integer id,@Param("previousReportingMonth") String previousReportingMonth);  
 
	@Query(" select m.id as id,m.name as name, m.description as description, pmd.comment as comment, pmd.statusId as statusId, pmd.id as projectMetricsTableId"
			+ ", (select s.description from Status s where s.id=pmd.statusId)as colour"
			+ " from  Metrics m ,ProjectMetricsDetail pmd where  m.id = pmd.metricsId AND pmd.projectId=:id AND pmd.reportedMonth =:currentReportedMonth ")
	List<Map> getMetricsNameByCurrentReportingMonthColour(@Param("id") Integer id,@Param("currentReportedMonth") String currentReportedMonth);
}
