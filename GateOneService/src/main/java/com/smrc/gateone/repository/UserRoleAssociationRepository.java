package com.smrc.gateone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.model.UserRoleAssociation;

@Repository
public interface UserRoleAssociationRepository extends JpaRepository<UserRoleAssociation, Integer> {

	List<UserRoleAssociation> findByRoleId(Integer userId);

	List<UserRoleAssociation> findAllByUserIdIn(List<Integer> userIdList);

}
