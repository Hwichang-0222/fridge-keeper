package org.fridgekeeper.mapper;

import org.fridgekeeper.dto.UserCategoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserCategoryMapperTest {
	@Autowired
	UserCategoryMapper userCategoryMapper;

	@Test
	void testInsertAndSelectUserCategory() {
		// 실제 DB 데이터에 맞춤: user_id=1, category_id=7
		UserCategoryDTO uc = new UserCategoryDTO();
		uc.setUserId(1L);
		uc.setCategoryId(1L); // 기본 카테고리 id 사용
		uc.setName("테스트유저카테고리");
		uc.setColor("#123456");
		userCategoryMapper.insertUserCategory(uc);
		List<UserCategoryDTO> list = userCategoryMapper.selectUserCategories(1L);
		assertThat(list).extracting("name").contains("테스트유저카테고리");
	}
	// ...existing code...

	@Test
	void testUserCategoryCRUD() {
		// 하나의 카테고리(user_id=3, category_id=3)로 CRUD 흐름 테스트
		UserCategoryDTO uc = new UserCategoryDTO();
		uc.setUserId(3L);
		uc.setCategoryId(3L); // 기본 카테고리 id 사용
		uc.setName("테스트유저카테고리");
		uc.setColor("#123456");
		// INSERT
		userCategoryMapper.insertUserCategory(uc);
		List<UserCategoryDTO> list = userCategoryMapper.selectUserCategories(3L);
		assertThat(list).extracting("name").contains("테스트유저카테고리");

		// UPDATE
		uc.setColor("#abcdef");
		uc.setName("업데이트유저카테고리");
		userCategoryMapper.updateUserCategory(uc);
		list = userCategoryMapper.selectUserCategories(3L);
		assertThat(list).extracting("color").contains("#abcdef");
		assertThat(list).extracting("name").contains("업데이트유저카테고리");

		// DELETE
		userCategoryMapper.deleteUserCategory(3L, 3L);
		list = userCategoryMapper.selectUserCategories(3L);
		boolean hasCategory3 = list.stream().anyMatch(dto -> dto.getCategoryId() == 3L);
		assertThat(hasCategory3).isFalse();
	}
}
