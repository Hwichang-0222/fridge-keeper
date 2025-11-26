package org.fridgekeeper.dto;

import lombok.Data;

@Data
public class ItemDTO {
	
	private Long itemId;
	private Long fridgeId;
	private Long categoryId;
	private String name;
	private String expirationDate;
	private String memo;
	private String createdAt;
	private String barcode;
}
