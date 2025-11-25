package org.fridgekeeper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.fridgekeeper.dto.CategoryDTO;
import java.util.List;

@Mapper
public interface CategoryMapper {
	List<CategoryDTO> selectAllCategories();

	CategoryDTO selectCategoryById(Long categoryId);

	int insertCategory(CategoryDTO category);

	int updateCategory(CategoryDTO category);

	int deleteCategory(Long categoryId);
}
