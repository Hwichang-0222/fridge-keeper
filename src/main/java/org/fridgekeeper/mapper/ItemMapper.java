package org.fridgekeeper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.fridgekeeper.dto.ItemDTO;
import java.util.List;

@Mapper
public interface ItemMapper {
	List<ItemDTO> selectAllItems();

	ItemDTO selectItemById(Long itemId);

	ItemDTO selectItemByBarcode(String barcode);

	int insertItem(ItemDTO item);

	int updateItem(ItemDTO item);

	int updateItemBarcode(Long itemId, String barcode);

	int deleteItem(Long itemId);
}
