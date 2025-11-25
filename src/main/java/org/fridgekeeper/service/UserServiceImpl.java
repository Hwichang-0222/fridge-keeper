package org.fridgekeeper.service;

import org.fridgekeeper.dto.UserDTO;
import org.fridgekeeper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	// 모든 사용자 목록 조회
	@Override
	public List<UserDTO> selectAllUsers() {
		return userMapper.selectAllUsers();
	}

	// 사용자 단건 조회
	@Override
	public UserDTO selectUserById(Long userId) {
		return userMapper.selectUserById(userId);
	}

	// 사용자 추가
	@Override
	public void insertUser(UserDTO userDTO) {
		// 비밀번호 해시 처리
		if (userDTO.getPassword() != null) {
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		}
		userMapper.insertUser(userDTO);
	}

	// 사용자 정보 수정
	@Override
	public void updateUser(UserDTO userDTO) {
		userMapper.updateUser(userDTO);
	}

	// 사용자 삭제
	@Override
	public void deleteUser(Long userId) {
		userMapper.deleteUser(userId);
	}
}
