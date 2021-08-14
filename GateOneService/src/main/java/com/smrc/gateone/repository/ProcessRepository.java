package com.smrc.gateone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smrc.gateone.model.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Integer>{

}
