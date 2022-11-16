package com.shree.compositeKey.mapper;


import com.shree.compositeKey.dto.ItemDTO;
import com.shree.compositeKey.entity.Item;
import org.mapstruct.Mapper;

@Mapper
public interface ItemMapper {
        Item toEntity(ItemDTO dto);
        ItemDTO toDto(Item dto);
}
