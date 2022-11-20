package com.shree.compositeKey.service;

import com.shree.compositeKey.dto.ItemDTO;
import com.shree.compositeKey.entity.Item;

import java.util.List;

public interface ItemService {
    ItemDTO getItem(Long id);
    ItemDTO saveItem(ItemDTO dto) throws Exception;
    void deleteItem(Long id) throws Exception;
    ItemDTO findById(Long id);
    Item toEntity(ItemDTO itemDTO);
    List<Item> findAll();
}
