package org.fridgekeeper.controller;

import org.fridgekeeper.dto.UserDTO;
import org.fridgekeeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// 모든 사용자 목록 조회
	@GetMapping
	public List<UserDTO> getAllUsers() {
		return userService.selectAllUsers();
	}

	// 사용자 단건 조회
	@GetMapping("/{userId}")
	public UserDTO getUserById(@PathVariable Long userId) {
		return userService.selectUserById(userId);
	}

	// 사용자 추가
	@PostMapping
	public void createUser(@RequestBody UserDTO userDTO) {
		userService.insertUser(userDTO);
	}

	// 로그인 엔드포인트
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO loginDTO) {
		UserDTO user = userService.selectUserById(loginDTO.getUserId());
		if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
			// 로그인 성공: 사용자 정보 반환(실제 서비스에서는 JWT 등 반환)
			return ResponseEntity.ok(user);
		} else {
			// 로그인 실패
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	// 사용자 정보 수정
	@PutMapping
	public void updateUser(@RequestBody UserDTO userDTO) {
		userService.updateUser(userDTO);
	}

	// 사용자 삭제
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}
}
