package com.shree.compositeKey.service;

import com.shree.compositeKey.dto.ItemDTO;
import com.shree.compositeKey.entity.Item;
import com.shree.compositeKey.mapper.ItemMapper;
import com.shree.compositeKey.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepo;

    private final ItemMapper itemMapper;

    @Override
    public ItemDTO getItem(Long id) {
        Item item;
        Optional<Item> optionalItem = itemRepo.findById(id);
        if (optionalItem.isPresent()) {
            item = optionalItem.get();
            return itemMapper.toDto(item);
        }
        else {
            return null;
        }
    }

    @Override
    public ItemDTO saveItem(ItemDTO dto) throws Exception {
        Item item = new Item();
        if (dto.getId() == null) {
            item = itemMapper.toEntity(dto);
            item = itemRepo.save(item);
        } else {
            log.error("id cannot be present for new entry");
            throw new Exception("id cannot be present for new entry");
        }
        return itemMapper.toDto(item);
    }

    @Override
    public void deleteItem(Long id) throws Exception {
        Optional<Item> item = itemRepo.findById(id);
        if (item.isPresent()) {
            itemRepo.deleteById(id);
        } else {
            log.error("Item with id {} not present", id);
            throw new Exception("Item with id " + id + " not present");
        }
    }

    @Override
    public ItemDTO findById(Long id) {
        Item item = itemRepo.findById(id).get();
        return itemMapper.toDto(item);
    }

    @Override
    public Item toEntity(ItemDTO itemDTO) {
        return itemMapper.toEntity(itemDTO);
    }


}
