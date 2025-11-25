package org.fridgekeeper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.fridgekeeper.dto.FridgeUserRoleDTO;
import java.util.List;

@Mapper
public interface FridgeUserRoleMapper {
	List<FridgeUserRoleDTO> selectRolesByFridgeId(Long fridgeId);

	List<FridgeUserRoleDTO> selectRolesByUserId(Long userId);

	int insertFridgeUserRole(FridgeUserRoleDTO role);

	int updateFridgeUserRole(FridgeUserRoleDTO role);

	int deleteFridgeUserRole(Long fridgeId, Long userId);
}
