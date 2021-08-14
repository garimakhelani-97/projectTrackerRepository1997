package com.smrc.api.users.data;

import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfoEntity, Long> {

	UserInfoEntity findByWindowUserId(String windowUserId);
	
	 UserInfoEntity findByWindowUserIdAndDomainName(String windowUserId, String domainName);
}
