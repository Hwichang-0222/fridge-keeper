package org.fridgekeeper.dto;

import lombok.Data;

@Data
public class UserCategoryDTO {
	private Long userId;
	private Long categoryId;
	private String name; // 사용자별 커스텀 이름(필요시)
	private String color; // 사용자별 커스텀 색상
}
