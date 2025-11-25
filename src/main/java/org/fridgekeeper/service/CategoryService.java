package org.fridgekeeper.service;

import org.fridgekeeper.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {

	// 모든 카테고리 목록 조회
	List<CategoryDTO> selectAllCategories();

	// 카테고리 단건 조회
	CategoryDTO selectCategoryById(Long categoryId);

	// 카테고리 추가
	void insertCategory(CategoryDTO categoryDTO);

	// 카테고리 정보 수정
	void updateCategory(CategoryDTO categoryDTO);

	// 카테고리 삭제
	void deleteCategory(Long categoryId);
}
