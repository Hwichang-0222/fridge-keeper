package org.fridgekeeper.controller;

import org.fridgekeeper.dto.FridgeUserRoleDTO;
import org.fridgekeeper.service.FridgeUserRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/fridge-user-roles")
public class FridgeUserRoleController {
	
	private final FridgeUserRoleService fridgeUserRoleService;

	public FridgeUserRoleController(FridgeUserRoleService fridgeUserRoleService) {
		this.fridgeUserRoleService = fridgeUserRoleService;
	}

	// 냉장고별 멤버 목록 조회 (오너만 가능)
	@GetMapping("/fridge/{fridgeId}")
	@PreAuthorize("@fridgeRoleChecker.isOwner(#fridgeId, authentication.name)")
	public List<FridgeUserRoleDTO> getRolesByFridge(@PathVariable Long fridgeId) {
		return fridgeUserRoleService.selectRolesByFridgeId(fridgeId);
	}

	// 멤버 추가 (오너만 가능)
	@PostMapping
	@PreAuthorize("@fridgeRoleChecker.isOwner(#fridgeUserRoleDTO.fridgeId, authentication.name)")
	public void addFridgeUserRole(@RequestBody FridgeUserRoleDTO fridgeUserRoleDTO) {
		fridgeUserRoleService.insertFridgeUserRole(fridgeUserRoleDTO);
	}

	// 멤버 삭제 (오너만 가능)
	@DeleteMapping
	@PreAuthorize("@fridgeRoleChecker.isOwner(#fridgeUserRoleDTO.fridgeId, authentication.name)")
	public void deleteFridgeUserRole(@RequestBody FridgeUserRoleDTO fridgeUserRoleDTO) {
		fridgeUserRoleService.deleteFridgeUserRole(fridgeUserRoleDTO.getFridgeId(), fridgeUserRoleDTO.getUserId());
	}
}
