package org.fridgekeeper.controller;

import org.springframework.security.test.context.support.WithMockUser;

import org.fridgekeeper.dto.FridgeDTO;
import org.fridgekeeper.service.FridgeService;
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

@WebMvcTest(FridgeController.class)
@WithMockUser
class FridgeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FridgeService fridgeService;

	@Test
	void getAllFridges_returnsOkAndList() throws Exception {
		FridgeDTO dto = new FridgeDTO();
		dto.setName("테스트냉장고");
		Mockito.when(fridgeService.selectAllFridges()).thenReturn(Collections.singletonList(dto));
		mockMvc.perform(get("/fridges"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("테스트냉장고"));
	}
}
