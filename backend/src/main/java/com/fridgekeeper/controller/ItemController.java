package com.fridgekeeper.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.fridgekeeper.domain.Item;
import com.fridgekeeper.service.ItemService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Item 관리", description = "냉장고 내 품목 CRUD API")
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // 안드로이드 연동 대비
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "전체 아이템 조회", description = "냉장고 안의 모든 아이템을 조회합니다.")
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @Operation(summary = "아이템 상세 조회", description = "ID를 이용해 특정 아이템 정보를 조회합니다.")
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @Operation(summary = "아이템 등록", description = "새로운 아이템을 추가합니다.")
    @PostMapping
    public void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @Operation(summary = "아이템 수정", description = "기존 아이템 정보를 수정합니다.")
    @PutMapping("/{id}")
    public void updateItem(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        itemService.updateItem(item);
    }

    @Operation(summary = "아이템 삭제", description = "ID를 이용해 아이템을 삭제합니다.")
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
