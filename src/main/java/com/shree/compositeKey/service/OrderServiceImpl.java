package com.shree.compositeKey.service;

import com.shree.compositeKey.dto.OrderDTO;
import com.shree.compositeKey.entity.Order;
import com.shree.compositeKey.entity.OrderItem;
import com.shree.compositeKey.mapper.OrderMapper;
import com.shree.compositeKey.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepo;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDTO getOrder(Long id) {
        Order order;
        Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isPresent()) {
            order = optionalOrder.get();
            OrderDTO orderDTO = orderMapper.toDto(order);
            calculateOrderTotal(orderDTO);
            return orderDTO;
        }
        else {
            return null;
        }
    }

    @Override
    public OrderDTO saveOrder(OrderDTO dto) throws Exception {
        Order order;
        if (dto.getId() == null) {
            order = orderMapper.toEntity(dto);
            order = orderRepo.save(order);
        } else {
            log.error("id cannot be present for new entry");
            throw new Exception("id cannot be present for new entry");
        }
        return orderMapper.toDto(order);
    }

    @Override
    public void deleteOrder(Long id) throws Exception {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            orderRepo.deleteById(id);
        } else {
            log.error("Order with id {} not present", id);
            throw new Exception("Order with id " + id + " not present");
        }
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        return orderMapper.toEntity(orderDTO);
    }

    @Override
    public OrderDTO toDto(Order order) {
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order = orderRepo.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDTO> getAll(Pageable pageable) {
        Page<Order> pageElements = orderRepo.findAll(pageable);
        List<Order> orderList = pageElements.getContent();
        if(!CollectionUtils.isEmpty(orderList)){
            return orderList.stream().map(order -> orderMapper.toDto(order))
                    .map(this::calculateOrderTotal)
                    .collect(Collectors.toList());
        }
        return null;
    }

    private OrderDTO calculateOrderTotal(OrderDTO order){
        Set<OrderItem> orderItemList = order.getOrderItems();
        if(!CollectionUtils.isEmpty(orderItemList)){
            order.setTotal(orderItemList.stream().mapToDouble(o->o.getQuantity()*o.getItem().getPrice()).sum());
        }
        return order;
    }

    @Override
    public OrderRepository getOrderRepo(){
        return orderRepo;
    }
}
