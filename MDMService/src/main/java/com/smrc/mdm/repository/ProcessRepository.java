package com.smrc.mdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smrc.mdm.model.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Integer>{

}
