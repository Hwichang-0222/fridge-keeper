package org.fridgekeeper.service;

import org.fridgekeeper.dto.ItemDTO;
import org.fridgekeeper.mapper.ItemMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

	private final ItemMapper itemMapper;

	public ItemServiceImpl(ItemMapper itemMapper) {
		this.itemMapper = itemMapper;
	}

	// 모든 아이템 목록 조회
	@Override
	public List<ItemDTO> selectAllItems() {
		return itemMapper.selectAllItems();
	}

	// 아이템 단건 조회
	@Override
	public ItemDTO selectItemById(Long itemId) {
		return itemMapper.selectItemById(itemId);
	}

	// 바코드로 아이템 조회
	@Override
	public ItemDTO selectItemByBarcode(String barcode) {
		return itemMapper.selectItemByBarcode(barcode);
	}

	// 아이템 추가
	@Override
	public void insertItem(ItemDTO itemDTO) {
		itemMapper.insertItem(itemDTO);
	}

	// 아이템 정보 수정
	@Override
	public void updateItem(ItemDTO itemDTO) {
		itemMapper.updateItem(itemDTO);
	}

	// 아이템 바코드만 수정
	@Override
	public void updateItemBarcode(Long itemId, String barcode) {
		itemMapper.updateItemBarcode(itemId, barcode);
	}

	// 아이템 삭제
	@Override
	public void deleteItem(Long itemId) {
		itemMapper.deleteItem(itemId);
	}
}
