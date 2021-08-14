package com.smrc.gpor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smrc.gpor.model.ActionMaster;

public interface ActionMasterRepository extends JpaRepository<ActionMaster, Integer>{
	
	public ActionMaster getActionMasterByActionKey(String actionKey);

@Query("select a.description from ActionMaster as a where a.actionKey=:actionKey ")	
	public String getDescription(String actionKey);
}
