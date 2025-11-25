package org.fridgekeeper.service;

import org.fridgekeeper.dto.FridgeDTO;
import java.util.List;

public interface FridgeService {
	
	// 모든 냉장고 목록 조회
	List<FridgeDTO> selectAllFridges();

	// 냉장고 단건 조회
	FridgeDTO selectFridgeById(Long fridgeId);

	// 냉장고 추가
	void insertFridge(FridgeDTO fridgeDTO);

	// 냉장고 정보 수정
	void updateFridge(FridgeDTO fridgeDTO);

	// 냉장고 삭제
	void deleteFridge(Long fridgeId);
}
