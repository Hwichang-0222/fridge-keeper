package com.fridgekeeper.domain;

import lombok.Data;

@Data
public class Item {
	
	private Long id;
	private String name;
	private int quantity;
	private String category;
	private String expirationDate;
	
}
