package org.fridgekeeper.controller;

import org.springframework.security.test.context.support.WithMockUser;

import org.fridgekeeper.dto.UserDTO;
import org.fridgekeeper.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
@WithMockUser
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	void getAllUsers_returnsOkAndList() throws Exception {
		UserDTO dto = new UserDTO();
		dto.setEmail("test@fridge.com");
		Mockito.when(userService.selectAllUsers()).thenReturn(Collections.singletonList(dto));
		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].email").value("test@fridge.com"));
	}
}
