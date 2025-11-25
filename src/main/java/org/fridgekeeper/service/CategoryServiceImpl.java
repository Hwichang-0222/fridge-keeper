package org.fridgekeeper.service;

import org.fridgekeeper.dto.CategoryDTO;
import org.fridgekeeper.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryMapper categoryMapper;

	public CategoryServiceImpl(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	// 모든 카테고리 목록 조회
	@Override
	public List<CategoryDTO> selectAllCategories() {
		return categoryMapper.selectAllCategories();
	}

	// 카테고리 단건 조회
	@Override
	public CategoryDTO selectCategoryById(Long categoryId) {
		return categoryMapper.selectCategoryById(categoryId);
	}

	// 카테고리 추가
	@Override
	public void insertCategory(CategoryDTO categoryDTO) {
		categoryMapper.insertCategory(categoryDTO);
	}

	// 카테고리 정보 수정
	@Override
	public void updateCategory(CategoryDTO categoryDTO) {
		categoryMapper.updateCategory(categoryDTO);
	}

	// 카테고리 삭제
	@Override
	public void deleteCategory(Long categoryId) {
		categoryMapper.deleteCategory(categoryId);
	}
}
