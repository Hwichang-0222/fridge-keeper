package org.fridgekeeper.controller;

import org.fridgekeeper.dto.FridgeDTO;
import org.fridgekeeper.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/fridges")
public class FridgeController {

	private final FridgeService fridgeService;

	@Autowired
	public FridgeController(FridgeService fridgeService) {
		this.fridgeService = fridgeService;
	}

	// 모든 냉장고 목록 조회
	@GetMapping
	public List<FridgeDTO> getAllFridges() {
		return fridgeService.selectAllFridges();
	}

	// 냉장고 단건 조회
	@GetMapping("/{fridgeId}")
	public FridgeDTO getFridgeById(@PathVariable Long fridgeId) {
		return fridgeService.selectFridgeById(fridgeId);
	}

	// 냉장고 추가
	@PostMapping
	public void createFridge(@RequestBody FridgeDTO fridgeDTO) {
		fridgeService.insertFridge(fridgeDTO);
	}

	// 냉장고 정보 수정 (오너만 가능)
	@PutMapping
	@PreAuthorize("@fridgeRoleChecker.isOwner(#fridgeDTO.fridgeId, authentication.name)")
	public void updateFridge(@RequestBody FridgeDTO fridgeDTO) {
		fridgeService.updateFridge(fridgeDTO);
	}

	// 냉장고 삭제 (오너만 가능)
	@DeleteMapping("/{fridgeId}")
	@PreAuthorize("@fridgeRoleChecker.isOwner(#fridgeId, authentication.name)")
	public void deleteFridge(@PathVariable Long fridgeId) {
		fridgeService.deleteFridge(fridgeId);
	}
}
