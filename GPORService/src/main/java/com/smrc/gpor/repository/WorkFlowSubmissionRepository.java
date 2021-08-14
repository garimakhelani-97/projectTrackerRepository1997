package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.WorkFlowSubmission;

@Repository
public interface WorkFlowSubmissionRepository extends JpaRepository<WorkFlowSubmission, Integer>{

	WorkFlowSubmission findFirstByProjectIdAndReportedMonth(Integer projectId, String reportedMonth, Sort sort);

}
