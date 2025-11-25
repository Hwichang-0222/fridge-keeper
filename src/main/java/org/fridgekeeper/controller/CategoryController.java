package org.fridgekeeper.controller;

import org.fridgekeeper.dto.CategoryDTO;
import org.fridgekeeper.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 모든 카테고리 목록 조회
	@GetMapping
	public List<CategoryDTO> getAllCategories() {
		return categoryService.selectAllCategories();
	}

	// 카테고리 단건 조회
	@GetMapping("/{categoryId}")
	public CategoryDTO getCategoryById(@PathVariable Long categoryId) {
		return categoryService.selectCategoryById(categoryId);
	}

	// 카테고리 추가
	@PostMapping
	public void createCategory(@RequestBody CategoryDTO categoryDTO) {
		categoryService.insertCategory(categoryDTO);
	}

	// 카테고리 정보 수정
	@PutMapping
	public void updateCategory(@RequestBody CategoryDTO categoryDTO) {
		categoryService.updateCategory(categoryDTO);
	}

	// 카테고리 삭제
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);
	}
}
