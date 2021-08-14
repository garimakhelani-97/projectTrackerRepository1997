package com.smrc.gpor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.WorkFlowApprover;

@Repository
public interface WorkFlowApproverRepository extends JpaRepository<WorkFlowApprover, Integer>{

}
