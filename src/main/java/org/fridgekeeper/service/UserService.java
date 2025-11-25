package org.fridgekeeper.service;

import org.fridgekeeper.dto.UserDTO;
import java.util.List;

public interface UserService {

	// 모든 사용자 목록 조회
	List<UserDTO> selectAllUsers();

	// 사용자 단건 조회
	UserDTO selectUserById(Long userId);

	// 사용자 추가
	void insertUser(UserDTO userDTO);

	// 사용자 정보 수정
	void updateUser(UserDTO userDTO);

	// 사용자 삭제
	void deleteUser(Long userId);
}
