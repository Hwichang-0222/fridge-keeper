package org.fridgekeeper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.fridgekeeper.dto.UserDTO;
import java.util.List;

@Mapper
public interface UserMapper {
	List<UserDTO> selectAllUsers();

	UserDTO selectUserById(Long userId);

	int insertUser(UserDTO user);

	int updateUser(UserDTO user);

	int deleteUser(Long userId);
}
