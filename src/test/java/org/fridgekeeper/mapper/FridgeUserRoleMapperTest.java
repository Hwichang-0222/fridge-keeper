package org.fridgekeeper.mapper;

import org.fridgekeeper.dto.FridgeUserRoleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FridgeUserRoleMapperTest {
	@Autowired
	FridgeUserRoleMapper fridgeUserRoleMapper;

	@Test
	void testInsertAndSelectRole() {
		// 부모 데이터 선삽입 (fridge, user)
		// ...existing code...
		fridgeUserRoleMapper.deleteFridgeUserRole(1L, 2L); // 중복키 방지
		FridgeUserRoleDTO role = new FridgeUserRoleDTO();
		role.setFridgeId(1L);
		role.setUserId(2L);
		role.setRole("OWNER");
		fridgeUserRoleMapper.insertFridgeUserRole(role);
		List<FridgeUserRoleDTO> roles = fridgeUserRoleMapper.selectRolesByFridgeId(1L);
		boolean hasRole = roles.stream()
				.anyMatch(r -> r.getFridgeId() == 1L && r.getUserId() == 2L && "OWNER".equals(r.getRole()));
		assertThat(hasRole).isTrue();
	}

	@Test
	void testUpdateRole() {
		// 부모 데이터 선삽입 (fridge, user)
		// ...existing code...
		fridgeUserRoleMapper.deleteFridgeUserRole(2L, 3L); // 중복키 방지
		FridgeUserRoleDTO role = new FridgeUserRoleDTO();
		role.setFridgeId(2L);
		role.setUserId(3L);
		role.setRole("MEMBER");
		fridgeUserRoleMapper.insertFridgeUserRole(role);
		role.setRole("OWNER");
		fridgeUserRoleMapper.updateFridgeUserRole(role);
		List<FridgeUserRoleDTO> roles = fridgeUserRoleMapper.selectRolesByFridgeId(2L);
		boolean hasRole = roles.stream()
				.anyMatch(r -> r.getFridgeId() == 2L && r.getUserId() == 3L && "OWNER".equals(r.getRole()));
		assertThat(hasRole).isTrue();
	}

	@Test
	void testDeleteRole() {
		// 부모 데이터 선삽입 (fridge, user)
		// ...existing code...
		fridgeUserRoleMapper.deleteFridgeUserRole(3L, 1L); // 중복키 방지
		FridgeUserRoleDTO role = new FridgeUserRoleDTO();
		role.setFridgeId(3L);
		role.setUserId(1L);
		role.setRole("MEMBER");
		fridgeUserRoleMapper.insertFridgeUserRole(role);
		fridgeUserRoleMapper.deleteFridgeUserRole(3L, 1L);
		List<FridgeUserRoleDTO> roles = fridgeUserRoleMapper.selectRolesByFridgeId(3L);
		boolean hasRole = roles.stream().anyMatch(r -> r.getFridgeId() == 3L && r.getUserId() == 1L);
		assertThat(hasRole).isFalse();
	}
}
