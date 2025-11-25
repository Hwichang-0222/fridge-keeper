package org.fridgekeeper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.fridgekeeper.dto.FridgeDTO;
import java.util.List;

@Mapper
public interface FridgeMapper {
	List<FridgeDTO> selectAllFridges();

	FridgeDTO selectFridgeById(Long fridgeId);

	int insertFridge(FridgeDTO fridge);

	int updateFridge(FridgeDTO fridge);

	int deleteFridge(Long fridgeId);
}
