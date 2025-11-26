package org.fridgekeeper.service;

import org.fridgekeeper.dto.ItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {
	@Autowired
	ItemService itemService;

	@Test
	void testInsertAndSelectItem() {
		ItemDTO item = new ItemDTO();
		item.setName("테스트아이템");
		item.setFridgeId(1L);
		item.setCategoryId(1L);
		itemService.insertItem(item);
		List<ItemDTO> items = itemService.selectAllItems();
		assertThat(items).extracting("name").contains("테스트아이템");
	}

	@Test
	void testUpdateItem() {
		ItemDTO item = new ItemDTO();
		item.setName("업데이트아이템");
		item.setFridgeId(2L);
		item.setCategoryId(2L);
		item.setBarcode("B-UPDATE");
		itemService.insertItem(item);
		ItemDTO inserted = itemService.selectAllItems().stream()
				.filter(i -> "업데이트아이템".equals(i.getName()))
				.findFirst().orElseThrow();
		item.setItemId(inserted.getItemId());
		item.setName("수정됨아이템");
		itemService.updateItem(item);
		ItemDTO found = itemService.selectItemById(item.getItemId());
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("수정됨아이템");
	}

	@Test
	void testDeleteItem() {
		ItemDTO item = new ItemDTO();
		item.setName("삭제아이템");
		item.setFridgeId(3L);
		item.setCategoryId(3L);
		item.setBarcode("B-DELETE");
		itemService.insertItem(item);
		ItemDTO inserted = itemService.selectAllItems().stream()
				.filter(i -> "삭제아이템".equals(i.getName()))
				.findFirst().orElseThrow();
		item.setItemId(inserted.getItemId());
		itemService.deleteItem(item.getItemId());
		ItemDTO found = itemService.selectItemById(item.getItemId());
		assertThat(found).isNull();
	}
}
