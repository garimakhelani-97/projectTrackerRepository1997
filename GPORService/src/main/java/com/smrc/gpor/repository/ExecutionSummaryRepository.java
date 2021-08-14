package com.smrc.gpor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.ExecutionSummary;

@Repository
public interface ExecutionSummaryRepository extends JpaRepository<ExecutionSummary, Integer>{

}
