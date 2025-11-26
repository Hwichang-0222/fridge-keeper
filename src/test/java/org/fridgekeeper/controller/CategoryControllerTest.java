package org.fridgekeeper.controller;

import org.springframework.security.test.context.support.WithMockUser;

import org.fridgekeeper.dto.CategoryDTO;
import org.fridgekeeper.service.CategoryService;
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

@WebMvcTest(CategoryController.class)
@WithMockUser
class CategoryControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;

	@Test
	void getAllCategories_returnsOkAndList() throws Exception {
		CategoryDTO dto = new CategoryDTO();
		dto.setName("테스트카테고리");
		Mockito.when(categoryService.selectAllCategories()).thenReturn(Collections.singletonList(dto));
		mockMvc.perform(get("/categories"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("테스트카테고리"));
	}
}
