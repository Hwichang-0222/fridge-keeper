package org.fridgekeeper.controller;

import org.springframework.security.test.context.support.WithMockUser;

import org.fridgekeeper.dto.ItemDTO;
import org.fridgekeeper.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ItemController.class)
@WithMockUser
class ItemControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemService itemService;

	@Test
	void getAllItems_returnsOkAndList() throws Exception {
		ItemDTO dto = new ItemDTO();
		dto.setName("테스트아이템");
		Mockito.when(itemService.selectAllItems()).thenReturn(Collections.singletonList(dto));
		mockMvc.perform(get("/items"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("테스트아이템"));
	}
}
