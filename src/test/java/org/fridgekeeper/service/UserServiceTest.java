package org.fridgekeeper.service;

import org.fridgekeeper.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
	@Autowired
	UserService userService;

	@Test
	void testInsertAndSelectUser() {
		UserDTO user = new UserDTO();
		user.setEmail("test@fridge.com");
		user.setNickname("테스터");
		user.setPassword("pw");
		userService.insertUser(user);
		List<UserDTO> users = userService.selectAllUsers();
		assertThat(users).extracting("email").contains("test@fridge.com");
	}

	@Test
	void testUpdateUser() {
		UserDTO user = new UserDTO();
		user.setEmail("update@fridge.com");
		user.setNickname("업데이트");
		user.setPassword("pw");
		userService.insertUser(user);
		UserDTO inserted = userService.selectAllUsers().stream()
				.filter(u -> "update@fridge.com".equals(u.getEmail()))
				.findFirst().orElseThrow();
		user.setUserId(inserted.getUserId());
		user.setNickname("수정됨");
		userService.updateUser(user);
		UserDTO found = userService.selectUserById(user.getUserId());
		assertThat(found).isNotNull();
		assertThat(found.getNickname()).isEqualTo("수정됨");
	}

	@Test
	void testDeleteUser() {
		UserDTO user = new UserDTO();
		user.setEmail("delete@fridge.com");
		user.setNickname("삭제");
		user.setPassword("pw");
		userService.insertUser(user);
		UserDTO inserted = userService.selectAllUsers().stream()
				.filter(u -> "delete@fridge.com".equals(u.getEmail()))
				.findFirst().orElseThrow();
		user.setUserId(inserted.getUserId());
		userService.deleteUser(user.getUserId());
		UserDTO found = userService.selectUserById(user.getUserId());
		assertThat(found).isNull();
	}
}
