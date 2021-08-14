package com.smrc.mdm.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.mdm.model.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer>{
	
	List<Unit> findByMindMdmUnitIdIn(Collection<Integer> ids);

}
