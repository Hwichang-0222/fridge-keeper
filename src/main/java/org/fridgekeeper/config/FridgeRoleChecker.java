package org.fridgekeeper.config;

import org.fridgekeeper.mapper.FridgeUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FridgeRoleChecker {
	
	private final FridgeUserRoleMapper fridgeUserRoleMapper;

	@Autowired
	public FridgeRoleChecker(FridgeUserRoleMapper fridgeUserRoleMapper) {
		this.fridgeUserRoleMapper = fridgeUserRoleMapper;
	}

	// fridgeId와 email로 OWNER 권한 확인
	public boolean isOwner(Long fridgeId, String email) {
		String role = fridgeUserRoleMapper.findRoleByFridgeIdAndEmail(fridgeId, email);
		return "OWNER".equals(role);
	}
}
