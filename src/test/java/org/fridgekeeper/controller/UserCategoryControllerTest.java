package org.fridgekeeper.controller;

import org.fridgekeeper.dto.UserCategoryDTO;
import org.fridgekeeper.service.UserCategoryService;
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

@WebMvcTest(UserCategoryController.class)
@WithMockUser
class UserCategoryControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserCategoryService userCategoryService;

	@Test
	void getUserCategories_returnsOkAndList() throws Exception {
		UserCategoryDTO dto = new UserCategoryDTO();
		dto.setCategoryId(1L);
		Mockito.when(userCategoryService.selectUserCategories(1L)).thenReturn(Collections.singletonList(dto));
		mockMvc.perform(get("/user-categories/user/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].categoryId").value(1L));
	}
}
