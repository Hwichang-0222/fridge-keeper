package org.fridgekeeper.controller;

import org.fridgekeeper.dto.ItemDTO;
import org.fridgekeeper.service.ItemService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	// 모든 아이템 목록 조회
	@GetMapping
	public List<ItemDTO> getAllItems() {
		return itemService.selectAllItems();
	}

	// 아이템 단건 조회
	@GetMapping("/{itemId}")
	public ItemDTO getItemById(@PathVariable Long itemId) {
		return itemService.selectItemById(itemId);
	}

	// 아이템 추가
	@PostMapping
	public void createItem(@RequestBody ItemDTO itemDTO) {
		itemService.insertItem(itemDTO);
	}

	// 아이템 정보 수정
	@PutMapping
	public void updateItem(@RequestBody ItemDTO itemDTO) {
		itemService.updateItem(itemDTO);
	}

	// 아이템 삭제
	@DeleteMapping("/{itemId}")
	public void deleteItem(@PathVariable Long itemId) {
		itemService.deleteItem(itemId);
	}
}
