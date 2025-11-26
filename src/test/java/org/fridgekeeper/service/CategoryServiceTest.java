package org.fridgekeeper.service;

import org.fridgekeeper.dto.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {
	@Autowired
	CategoryService categoryService;

	@Test
	void testInsertAndSelectCategory() {
		CategoryDTO category = new CategoryDTO();
		category.setName("테스트카테고리");
		category.setColor("#123456");
		categoryService.insertCategory(category);
		List<CategoryDTO> categories = categoryService.selectAllCategories();
		assertThat(categories).extracting("name").contains("테스트카테고리");
	}

	@Test
	void testUpdateCategory() {
		CategoryDTO category = new CategoryDTO();
		category.setName("업데이트카테고리");
		category.setColor("#654321");
		categoryService.insertCategory(category);
		CategoryDTO inserted = categoryService.selectAllCategories().stream()
				.filter(c -> "업데이트카테고리".equals(c.getName()))
				.findFirst().orElseThrow();
		category.setCategoryId(inserted.getCategoryId());
		category.setColor("#abcdef");
		categoryService.updateCategory(category);
		CategoryDTO found = categoryService.selectCategoryById(category.getCategoryId());
		assertThat(found).isNotNull();
		assertThat(found.getColor()).isEqualTo("#abcdef");
	}

	@Test
	void testDeleteCategory() {
		CategoryDTO category = new CategoryDTO();
		category.setName("삭제카테고리");
		category.setColor("#000000");
		categoryService.insertCategory(category);
		CategoryDTO inserted = categoryService.selectAllCategories().stream()
				.filter(c -> "삭제카테고리".equals(c.getName()))
				.findFirst().orElseThrow();
		category.setCategoryId(inserted.getCategoryId());
		categoryService.deleteCategory(category.getCategoryId());
		CategoryDTO found = categoryService.selectCategoryById(category.getCategoryId());
		assertThat(found).isNull();
	}
}
