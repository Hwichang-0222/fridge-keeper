package org.fridgekeeper.service;

import org.fridgekeeper.dto.UserCategoryDTO;
import org.fridgekeeper.mapper.UserCategoryMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {
	private final UserCategoryMapper userCategoryMapper;

	public UserCategoryServiceImpl(UserCategoryMapper userCategoryMapper) {
		this.userCategoryMapper = userCategoryMapper;
	}

	@Override
	// 사용자별 카테고리 목록 조회
	public List<UserCategoryDTO> selectUserCategories(Long userId) {
		return userCategoryMapper.selectUserCategories(userId);
	}

	@Override
	// 사용자별 카테고리 연결(추가)
	public void insertUserCategory(UserCategoryDTO userCategoryDTO) {
		userCategoryMapper.insertUserCategory(userCategoryDTO);
	}

	@Override
	// 사용자별 카테고리 정보 수정
	public void updateUserCategory(UserCategoryDTO userCategoryDTO) {
		userCategoryMapper.updateUserCategory(userCategoryDTO);
	}

	@Override
	// 사용자별 카테고리 연결 해제(삭제)
	public void deleteUserCategory(Long userId, Long categoryId) {
		userCategoryMapper.deleteUserCategory(userId, categoryId);
	}

	@Override
	// 카테고리 사용 중인 사용자 수 조회
	public int countUsersByCategoryId(Long categoryId) {
		return userCategoryMapper.countUsersByCategoryId(categoryId);
	}
}
