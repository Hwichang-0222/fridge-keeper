package org.fridgekeeper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.fridgekeeper.dto.UserCategoryDTO;
import java.util.List;

@Mapper
public interface UserCategoryMapper {

	List<UserCategoryDTO> selectUserCategories(Long userId);

	int insertUserCategory(UserCategoryDTO userCategory);

	int updateUserCategory(UserCategoryDTO userCategory);

    int deleteUserCategory(Long userId, Long categoryId);
}
