package org.fridgekeeper.service;

import org.fridgekeeper.dto.UserCategoryDTO;
import java.util.List;

public interface UserCategoryService {
	// 사용자별 카테고리 목록 조회
	List<UserCategoryDTO> selectUserCategories(Long userId);

	// 사용자별 카테고리 연결(추가)
	void insertUserCategory(UserCategoryDTO userCategoryDTO);

	// 사용자별 카테고리 정보 수정
	void updateUserCategory(UserCategoryDTO userCategoryDTO);

	// 사용자별 카테고리 연결 해제(삭제)
	void deleteUserCategory(Long userId, Long categoryId);

	// 카테고리 사용 중인 사용자 수 조회
	int countUsersByCategoryId(Long categoryId);
}
