package org.fridgekeeper.service;

import org.fridgekeeper.dto.FridgeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FridgeServiceTest {
	@Autowired
	FridgeService fridgeService;

	@Test
	void testInsertAndSelectFridge() {
		FridgeDTO fridge = new FridgeDTO();
		fridge.setName("테스트냉장고");
		fridgeService.insertFridge(fridge);
		List<FridgeDTO> fridges = fridgeService.selectAllFridges();
		assertThat(fridges).extracting("name").contains("테스트냉장고");
	}

	@Test
	void testUpdateFridge() {
		FridgeDTO fridge = new FridgeDTO();
		fridge.setName("업데이트냉장고");
		fridgeService.insertFridge(fridge);
		FridgeDTO inserted = fridgeService.selectAllFridges().stream()
				.filter(f -> "업데이트냉장고".equals(f.getName()))
				.findFirst().orElseThrow();
		fridge.setFridgeId(inserted.getFridgeId());
		fridge.setName("수정됨냉장고");
		fridgeService.updateFridge(fridge);
		FridgeDTO found = fridgeService.selectFridgeById(fridge.getFridgeId());
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("수정됨냉장고");
	}

	@Test
	void testDeleteFridge() {
		FridgeDTO fridge = new FridgeDTO();
		fridge.setName("삭제냉장고");
		fridgeService.insertFridge(fridge);
		FridgeDTO inserted = fridgeService.selectAllFridges().stream()
				.filter(f -> "삭제냉장고".equals(f.getName()))
				.findFirst().orElseThrow();
		fridge.setFridgeId(inserted.getFridgeId());
		fridgeService.deleteFridge(fridge.getFridgeId());
		FridgeDTO found = fridgeService.selectFridgeById(fridge.getFridgeId());
		assertThat(found).isNull();
	}
}
