package com.fridgekeeper.service;

import java.util.List;

import com.fridgekeeper.domain.Item;

public interface ItemService {
	
	List<Item> getAllItems();
	
	Item getItemById(Long id);
	
	void addItem(Item item);
	
	void updateItem(Item item);
	
	void deleteItem(Long id);
	

}
