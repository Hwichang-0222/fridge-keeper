package org.fridgekeeper.service;

import org.fridgekeeper.dto.FridgeUserRoleDTO;
import org.fridgekeeper.mapper.FridgeUserRoleMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FridgeUserRoleServiceImpl implements FridgeUserRoleService {

	private final FridgeUserRoleMapper fridgeUserRoleMapper;

	public FridgeUserRoleServiceImpl(FridgeUserRoleMapper fridgeUserRoleMapper) {
		this.fridgeUserRoleMapper = fridgeUserRoleMapper;
	}

	// 냉장고별 멤버 목록 조회
	@Override
	public List<FridgeUserRoleDTO> selectRolesByFridgeId(Long fridgeId) {
		return fridgeUserRoleMapper.selectRolesByFridgeId(fridgeId);
	}

	// 사용자별 냉장고 권한 목록 조회
	@Override
	public List<FridgeUserRoleDTO> selectRolesByUserId(Long userId) {
		return fridgeUserRoleMapper.selectRolesByUserId(userId);
	}

	// 멤버 추가
	@Override
	public void insertFridgeUserRole(FridgeUserRoleDTO roleDTO) {
		fridgeUserRoleMapper.insertFridgeUserRole(roleDTO);
	}

	// 멤버 권한 수정
	@Override
	public void updateFridgeUserRole(FridgeUserRoleDTO roleDTO) {
		fridgeUserRoleMapper.updateFridgeUserRole(roleDTO);
	}

	// 멤버 삭제
	@Override
	public void deleteFridgeUserRole(Long fridgeId, Long userId) {
		fridgeUserRoleMapper.deleteFridgeUserRole(fridgeId, userId);
	}
}
