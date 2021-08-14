package com.smrc.gateone.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.model.Metrics;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics, Integer> {
	
	@Query("select m.id as id,m.name as name, m.description as description, pmd.comment as comment, pmd.id as projectMetricsTableId"
			+ "  from Metrics m left join ProjectMetricsDetail pmd on m.id = pmd.metricsId AND pmd.projectId=:id")
	List<Map> getMetricsDetailsByProjectId(@Param("id") Integer id);

}
