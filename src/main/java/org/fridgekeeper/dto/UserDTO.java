package org.fridgekeeper.dto;

import lombok.Data;

@Data
public class UserDTO {
	private Long userId;
	private String email;
	private String nickname;
	private String createdAt;
}
