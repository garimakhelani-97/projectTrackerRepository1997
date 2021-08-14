package com.smrc.gpor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.WorkflowDetails;

@Repository
public interface WorkflowDetailRepository extends JpaRepository<WorkflowDetails, Integer> {

}
