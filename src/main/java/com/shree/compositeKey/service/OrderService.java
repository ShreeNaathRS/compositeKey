package com.shree.compositeKey.service;

import com.shree.compositeKey.dto.OrderDTO;
import com.shree.compositeKey.entity.Order;
import com.shree.compositeKey.repository.OrderRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderDTO getOrder(Long id);
    OrderDTO saveOrder(OrderDTO dto) throws Exception;
    void deleteOrder(Long id) throws Exception;
    Order toEntity(OrderDTO orderDTO);
    OrderDTO toDto(Order order);
    OrderDTO update(OrderDTO orderDTO);
    OrderRepository getOrderRepo();
    List<OrderDTO> getAll(Pageable pageRequest);
}
