package com.smrc.gateone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.model.Gate;
@Repository
public interface GateRepository extends JpaRepository<Gate, Integer> {

	List<Gate> findAllByOrderByOrderAsc();

}
