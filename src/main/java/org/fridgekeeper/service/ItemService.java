package org.fridgekeeper.service;

import org.fridgekeeper.dto.ItemDTO;
import java.util.List;

public interface ItemService {

	// 모든 아이템 목록 조회
	List<ItemDTO> selectAllItems();

	// 아이템 단건 조회
	ItemDTO selectItemById(Long itemId);

	// 바코드로 아이템 조회
	ItemDTO selectItemByBarcode(String barcode);

	// 아이템 추가
	void insertItem(ItemDTO itemDTO);

	// 아이템 정보 수정
	void updateItem(ItemDTO itemDTO);

	// 아이템 바코드만 수정
	void updateItemBarcode(Long itemId, String barcode);

	// 아이템 삭제
	void deleteItem(Long itemId);
}
