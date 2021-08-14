package com.smrc.gpor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.WorkFlow;

@Repository
public interface WorkFlowRepository extends JpaRepository<WorkFlow, Integer> {

}
