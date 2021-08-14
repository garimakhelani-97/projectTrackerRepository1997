package com.smrc.gpor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.PreviewsScreen;

@Repository
public interface PreviewsScreenRepository extends JpaRepository<PreviewsScreen, Integer>{
	
	public PreviewsScreen findByProjectIdAndReportedMonth(Integer projectId, String reportedMonth);

}
