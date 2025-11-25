package org.fridgekeeper.service;

import org.fridgekeeper.dto.FridgeUserRoleDTO;
import java.util.List;

public interface FridgeUserRoleService {

	// 냉장고별 멤버 목록 조회
	List<FridgeUserRoleDTO> selectRolesByFridgeId(Long fridgeId);

	// 사용자별 냉장고 권한 목록 조회
	List<FridgeUserRoleDTO> selectRolesByUserId(Long userId);

	// 멤버 추가
	void insertFridgeUserRole(FridgeUserRoleDTO roleDTO);

	// 멤버 권한 수정
	void updateFridgeUserRole(FridgeUserRoleDTO roleDTO);

	// 멤버 삭제
	void deleteFridgeUserRole(Long fridgeId, Long userId);
}
