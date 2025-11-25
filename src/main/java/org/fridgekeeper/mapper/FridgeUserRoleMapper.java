package org.fridgekeeper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.fridgekeeper.dto.FridgeUserRoleDTO;
import java.util.List;

@Mapper
public interface FridgeUserRoleMapper {

	// 냉장고별 멤버 목록 조회
	List<FridgeUserRoleDTO> selectRolesByFridgeId(Long fridgeId);

	// 사용자별 냉장고 권한 목록 조회
	List<FridgeUserRoleDTO> selectRolesByUserId(Long userId);

	// 멤버 추가
	int insertFridgeUserRole(FridgeUserRoleDTO role);

	// 멤버 권한 수정
	int updateFridgeUserRole(FridgeUserRoleDTO role);

	// 멤버 삭제
	int deleteFridgeUserRole(Long fridgeId, Long userId);

	// fridgeId와 email로 권한 문자열 반환
	String findRoleByFridgeIdAndEmail(Long fridgeId, String email);
}
