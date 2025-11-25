package org.fridgekeeper.dto;

import lombok.Data;

@Data
public class CategoryDTO {
	private Long categoryId;
	private String name;
	private String color;
	private Long createdByUserId; // null이면 기본 카테고리
}
