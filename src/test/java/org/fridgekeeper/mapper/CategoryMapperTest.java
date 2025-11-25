package org.fridgekeeper.mapper;

import org.fridgekeeper.dto.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryMapperTest {

	@Autowired
	CategoryMapper categoryMapper;

	@Test
	void testInsertAndSelectCategory() {
		CategoryDTO category = new CategoryDTO();
		category.setName("테스트카테고리");
		category.setColor("#123456");
		categoryMapper.insertCategory(category);
		List<CategoryDTO> categories = categoryMapper.selectAllCategories();
		assertThat(categories).extracting("name").contains("테스트카테고리");
	}

	@Test
	void testUpdateCategory() {
		CategoryDTO category = new CategoryDTO();
		category.setName("업데이트카테고리");
		category.setColor("#654321");
		categoryMapper.insertCategory(category);
		CategoryDTO inserted = categoryMapper.selectAllCategories().stream()
				.filter(c -> "업데이트카테고리".equals(c.getName()))
				.findFirst().orElseThrow();
		category.setCategoryId(inserted.getCategoryId());
		category.setColor("#abcdef");
		categoryMapper.updateCategory(category);
		CategoryDTO found = categoryMapper.selectCategoryById(category.getCategoryId());
		assertThat(found).isNotNull();
		assertThat(found.getColor()).isEqualTo("#abcdef");
	}

	@Test
	void testDeleteCategory() {
		CategoryDTO category = new CategoryDTO();
		category.setName("삭제카테고리");
		category.setColor("#000000");
		categoryMapper.insertCategory(category);
		CategoryDTO inserted = categoryMapper.selectAllCategories().stream()
				.filter(c -> "삭제카테고리".equals(c.getName()))
				.findFirst().orElseThrow();
		category.setCategoryId(inserted.getCategoryId());
		categoryMapper.deleteCategory(category.getCategoryId());
		CategoryDTO found = categoryMapper.selectCategoryById(category.getCategoryId());
		assertThat(found).isNull();
	}
}
