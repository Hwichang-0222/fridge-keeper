package org.fridgekeeper.controller;

import org.fridgekeeper.dto.FridgeUserRoleDTO;
import org.fridgekeeper.service.FridgeUserRoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(FridgeUserRoleController.class)
@WithMockUser
class FridgeUserRoleControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FridgeUserRoleService fridgeUserRoleService;

	@Test
	void getRolesByFridge_returnsOkAndList() throws Exception {
		FridgeUserRoleDTO dto = new FridgeUserRoleDTO();
		dto.setRole("OWNER");
		Mockito.when(fridgeUserRoleService.selectRolesByFridgeId(1L)).thenReturn(Collections.singletonList(dto));
		mockMvc.perform(get("/fridge-user-roles/fridge/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].role").value("OWNER"));
	}
}
