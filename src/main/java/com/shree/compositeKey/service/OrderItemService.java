package com.shree.compositeKey.service;

import com.shree.compositeKey.dto.OrderItemDTO;
import com.shree.compositeKey.entity.OrderItem;

public interface OrderItemService {
    OrderItemDTO getOrderItem(Long id);
    OrderItemDTO saveOrderItem(OrderItemDTO dto) throws Exception;
    void deleteOrderItem(Long id) throws Exception;
    OrderItem toEntity(OrderItemDTO OrderItemDTO);
    OrderItemDTO toDto(OrderItem OrderItem);
    OrderItemDTO update(OrderItemDTO OrderItemDTO);
}
