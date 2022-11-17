package com.shree.compositeKey.service;

import com.shree.compositeKey.dto.OrderItemDTO;
import com.shree.compositeKey.entity.OrderItem;
import com.shree.compositeKey.mapper.OrderItemMapper;
import com.shree.compositeKey.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl implements OrderItemService{
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemDTO getOrderItem(Long id) {
        OrderItem OrderItem;
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(id);
        if (optionalOrderItem.isPresent()) {
            OrderItem = optionalOrderItem.get();
            return orderItemMapper.toDto(OrderItem);
        }
        else {
            return null;
        }
    }

    @Override
    public OrderItemDTO saveOrderItem(OrderItemDTO dto) throws Exception {
        OrderItem orderItem;
        if (dto.getId() == null) {
            orderItem = orderItemMapper.toEntity(dto);
            orderItem = orderItemRepository.save(orderItem);
        } else {
            log.error("id cannot be present for new entry");
            throw new Exception("id cannot be present for new entry");
        }
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) throws Exception {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isPresent()) {
            orderItemRepository.deleteById(id);
        } else {
            log.error("OrderItem with id {} not present", id);
            throw new Exception("OrderItem with id " + id + " not present");
        }
    }

    @Override
    public OrderItem toEntity(OrderItemDTO orderItemDTO) {
        return orderItemMapper.toEntity(orderItemDTO);
    }

    @Override
    public OrderItemDTO toDto(OrderItem orderItem) {
        OrderItemDTO itemDTO = orderItemMapper.toDto(orderItem);
        return itemDTO;
    }

    @Override
    public OrderItemDTO update(OrderItemDTO orderItemDTO) {
        OrderItem OrderItem = orderItemMapper.toEntity(orderItemDTO);
        OrderItem = orderItemRepository.save(OrderItem);
        return orderItemMapper.toDto(OrderItem);
    }
}
