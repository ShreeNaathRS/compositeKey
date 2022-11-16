package com.shree.compositeKey.mapper;

import com.shree.compositeKey.dto.OrderDTO;
import com.shree.compositeKey.entity.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
        Order toEntity(OrderDTO dto);
        OrderDTO toDto(Order dto);
}
