package org.fridgekeeper.service;

import org.fridgekeeper.dto.UserCategoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserCategoryServiceTest {
	@Autowired
	UserCategoryService userCategoryService;

	@Test
	void testInsertAndSelectUserCategory() {
		UserCategoryDTO uc = new UserCategoryDTO();
		uc.setUserId(1L);
		uc.setCategoryId(1L);
		uc.setName("테스트유저카테고리");
		uc.setColor("#123456");
		userCategoryService.insertUserCategory(uc);
		List<UserCategoryDTO> list = userCategoryService.selectUserCategories(1L);
		assertThat(list).extracting("name").contains("테스트유저카테고리");
	}

	@Test
	void testUserCategoryCRUD() {
		UserCategoryDTO uc = new UserCategoryDTO();
		uc.setUserId(3L);
		uc.setCategoryId(3L);
		uc.setName("테스트유저카테고리");
		uc.setColor("#123456");
		userCategoryService.insertUserCategory(uc);
		List<UserCategoryDTO> list = userCategoryService.selectUserCategories(3L);
		assertThat(list).extracting("name").contains("테스트유저카테고리");

		uc.setColor("#abcdef");
		uc.setName("업데이트유저카테고리");
		userCategoryService.updateUserCategory(uc);
		list = userCategoryService.selectUserCategories(3L);
		assertThat(list).extracting("color").contains("#abcdef");
		assertThat(list).extracting("name").contains("업데이트유저카테고리");

		userCategoryService.deleteUserCategory(3L, 3L);
		list = userCategoryService.selectUserCategories(3L);
		boolean hasCategory3 = list.stream().anyMatch(dto -> dto.getCategoryId() == 3L);
		assertThat(hasCategory3).isFalse();
	}
}
