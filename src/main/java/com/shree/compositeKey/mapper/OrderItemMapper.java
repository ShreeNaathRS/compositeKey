package com.shree.compositeKey.mapper;

import com.shree.compositeKey.dto.OrderItemDTO;
import com.shree.compositeKey.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper
public interface OrderItemMapper {
        OrderItem toEntity(OrderItemDTO dto);
        OrderItemDTO toDto(OrderItem dto);
}
