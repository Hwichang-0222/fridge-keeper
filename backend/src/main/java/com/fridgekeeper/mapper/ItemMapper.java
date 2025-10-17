package com.fridgekeeper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fridgekeeper.domain.Item;

@Mapper
public interface ItemMapper {
	
	// 전체 목록 조회
	List<Item> getItems();
	
	// 단일 항목 조회
	Item getItemById(Long id);

	// 등록
	void insertItem(Item item);
	
	// 수정
	void updateItem(Item item);
	
	// 삭제
	void deleteItem(Long id);

}
