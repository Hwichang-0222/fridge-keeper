package org.fridgekeeper.controller;

import org.fridgekeeper.dto.UserCategoryDTO;
import org.fridgekeeper.service.UserCategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user-categories")
public class UserCategoryController {
	
	private final UserCategoryService userCategoryService;

	public UserCategoryController(UserCategoryService userCategoryService) {
		this.userCategoryService = userCategoryService;
	}

	// 사용자별 카테고리 목록 조회
	@GetMapping("/user/{userId}")
	public List<UserCategoryDTO> getUserCategories(@PathVariable Long userId) {
		return userCategoryService.selectUserCategories(userId);
	}

	// 사용자별 카테고리 연결(추가)
	@PostMapping
	public void addUserCategory(@RequestBody UserCategoryDTO userCategoryDTO) {
		userCategoryService.insertUserCategory(userCategoryDTO);
	}

	// 사용자별 카테고리 연결 해제(삭제)
	@DeleteMapping
	public void deleteUserCategory(@RequestBody UserCategoryDTO userCategoryDTO) {
		userCategoryService.deleteUserCategory(userCategoryDTO.getUserId(), userCategoryDTO.getCategoryId());
	}

	// 카테고리를 사용 중인 사용자 수 조회 (카테고리 추가 시 참고용)
	@GetMapping("/count/{categoryId}")
	public int countUsersByCategory(@PathVariable Long categoryId) {
		return userCategoryService.countUsersByCategoryId(categoryId);
	}

	// 추천/공유/복사 등 확장 기능은 필요시 추가
}
