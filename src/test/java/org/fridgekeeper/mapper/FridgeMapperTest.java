package org.fridgekeeper.mapper;

import org.fridgekeeper.dto.FridgeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FridgeMapperTest {
	@Autowired
	FridgeMapper fridgeMapper;

	@Test
	void testInsertAndSelectFridge() {
		FridgeDTO fridge = new FridgeDTO();
		fridge.setName("테스트냉장고");
		fridgeMapper.insertFridge(fridge);
		List<FridgeDTO> fridges = fridgeMapper.selectAllFridges();
		assertThat(fridges).extracting("name").contains("테스트냉장고");
	}

	@Test
	void testUpdateFridge() {
		FridgeDTO fridge = new FridgeDTO();
		fridge.setName("업데이트냉장고");
		fridgeMapper.insertFridge(fridge);
		FridgeDTO inserted = fridgeMapper.selectAllFridges().stream()
				.filter(f -> "업데이트냉장고".equals(f.getName()))
				.findFirst().orElseThrow();
		fridge.setFridgeId(inserted.getFridgeId());
		fridge.setName("수정됨냉장고");
		fridgeMapper.updateFridge(fridge);
		FridgeDTO found = fridgeMapper.selectFridgeById(fridge.getFridgeId());
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("수정됨냉장고");
	}

	@Test
	void testDeleteFridge() {
		FridgeDTO fridge = new FridgeDTO();
		fridge.setName("삭제냉장고");
		fridgeMapper.insertFridge(fridge);
		FridgeDTO inserted = fridgeMapper.selectAllFridges().stream()
				.filter(f -> "삭제냉장고".equals(f.getName()))
				.findFirst().orElseThrow();
		fridge.setFridgeId(inserted.getFridgeId());
		fridgeMapper.deleteFridge(fridge.getFridgeId());
		FridgeDTO found = fridgeMapper.selectFridgeById(fridge.getFridgeId());
		assertThat(found).isNull();
	}
}
