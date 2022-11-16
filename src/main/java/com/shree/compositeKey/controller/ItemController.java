package com.shree.compositeKey.controller;

import com.shree.compositeKey.dto.ItemDTO;
import com.shree.compositeKey.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/item/{id}")
    ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
        ItemDTO itemDto = itemService.getItem(id);
        return ResponseEntity.ok(itemDto);
    }

    @PostMapping("/item/save")
    ResponseEntity<ItemDTO> saveItem(@RequestBody ItemDTO dto) throws Exception {
        ItemDTO itemDto = itemService.saveItem(dto);
        return ResponseEntity.created(new URI("/item/save")).body(itemDto);
    }

    @DeleteMapping("/item/delete/{id}")
    ResponseEntity<Void> deleteItem(@PathVariable Long id) throws Exception {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

}
