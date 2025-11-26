package org.fridgekeeper.dto;

import lombok.Data;

@Data
public class FridgeUserRoleDTO {
	
	private Long fridgeId;
	private Long userId;
	private String role; // OWNER, MEMBER
	private String joinedAt;
}