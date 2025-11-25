package org.fridgekeeper.service;

import org.fridgekeeper.dto.FridgeDTO;
import org.fridgekeeper.mapper.FridgeMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FridgeServiceImpl implements FridgeService {

	private final FridgeMapper fridgeMapper;

	public FridgeServiceImpl(FridgeMapper fridgeMapper) {
		this.fridgeMapper = fridgeMapper;
	}

	// 모든 냉장고 목록 조회
	@Override
	public List<FridgeDTO> selectAllFridges() {
		return fridgeMapper.selectAllFridges();
	}

	// 냉장고 단건 조회
	@Override
	public FridgeDTO selectFridgeById(Long fridgeId) {
		return fridgeMapper.selectFridgeById(fridgeId);
	}

	// 냉장고 추가
	@Override
	public void insertFridge(FridgeDTO fridgeDTO) {
		fridgeMapper.insertFridge(fridgeDTO);
	}

	// 냉장고 정보 수정
	@Override
	public void updateFridge(FridgeDTO fridgeDTO) {
		fridgeMapper.updateFridge(fridgeDTO);
	}

	// 냉장고 삭제
	@Override
	public void deleteFridge(Long fridgeId) {
		fridgeMapper.deleteFridge(fridgeId);
	}
}
