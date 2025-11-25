package com.fridgekeeper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fridgekeeper.domain.Item;
import com.fridgekeeper.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
	
	private final ItemMapper itemMapper;
	
	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return itemMapper.getItems();
	}
	
	@Override
	public Item getItemById(Long id) {
		// TODO Auto-generated method stub
		return itemMapper.getItemById(id);
	}
	
	@Override
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		itemMapper.insertItem(item);
	}
	
	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		itemMapper.updateItem(item);
	}
	
	@Override
	public void deleteItem(Long id) {
		// TODO Auto-generated method stub
		itemMapper.deleteItem(id);
	}
	

}
