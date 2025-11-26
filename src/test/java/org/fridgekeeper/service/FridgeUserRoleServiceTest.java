package org.fridgekeeper.service;

import org.fridgekeeper.dto.FridgeUserRoleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FridgeUserRoleServiceTest {
	@Autowired
	FridgeUserRoleService fridgeUserRoleService;

	@Test
	void testInsertAndSelectRole() {
		FridgeUserRoleDTO role = new FridgeUserRoleDTO();
		role.setFridgeId(1L);
		role.setUserId(2L);
		role.setRole("OWNER");
		fridgeUserRoleService.insertFridgeUserRole(role);
		List<FridgeUserRoleDTO> roles = fridgeUserRoleService.selectRolesByFridgeId(1L);
		boolean hasRole = roles.stream()
				.anyMatch(r -> r.getFridgeId() == 1L && r.getUserId() == 2L && "OWNER".equals(r.getRole()));
		assertThat(hasRole).isTrue();
	}

	@Test
	void testUpdateRole() {
		FridgeUserRoleDTO role = new FridgeUserRoleDTO();
		role.setFridgeId(2L);
		role.setUserId(3L);
		role.setRole("MEMBER");
		fridgeUserRoleService.insertFridgeUserRole(role);
		role.setRole("OWNER");
		fridgeUserRoleService.updateFridgeUserRole(role);
		List<FridgeUserRoleDTO> roles = fridgeUserRoleService.selectRolesByFridgeId(2L);
		boolean hasRole = roles.stream()
				.anyMatch(r -> r.getFridgeId() == 2L && r.getUserId() == 3L && "OWNER".equals(r.getRole()));
		assertThat(hasRole).isTrue();
	}

	@Test
	void testDeleteRole() {
		FridgeUserRoleDTO role = new FridgeUserRoleDTO();
		role.setFridgeId(3L);
		role.setUserId(1L);
		role.setRole("MEMBER");
		fridgeUserRoleService.insertFridgeUserRole(role);
		fridgeUserRoleService.deleteFridgeUserRole(3L, 1L);
		List<FridgeUserRoleDTO> roles = fridgeUserRoleService.selectRolesByFridgeId(3L);
		boolean hasRole = roles.stream().anyMatch(r -> r.getFridgeId() == 3L && r.getUserId() == 1L);
		assertThat(hasRole).isFalse();
	}
}
