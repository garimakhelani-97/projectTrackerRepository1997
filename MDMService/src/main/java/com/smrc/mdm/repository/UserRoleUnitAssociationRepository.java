package com.smrc.mdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.mdm.model.UserRoleUnitAssociation;

@Repository
public interface UserRoleUnitAssociationRepository extends JpaRepository<UserRoleUnitAssociation, Integer> {

	List<UserRoleUnitAssociation> findByRoleId(Integer userId);

}
