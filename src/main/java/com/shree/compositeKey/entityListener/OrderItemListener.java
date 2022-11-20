package com.shree.compositeKey.entityListener;

import com.shree.compositeKey.dto.OrderDTO;
import com.shree.compositeKey.entity.Order;
import com.shree.compositeKey.entity.OrderItem;
import com.shree.compositeKey.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Slf4j
@Component
public class OrderItemListener {

    private final OrderService orderService;

    public OrderItemListener(@Lazy OrderService orderService) {
        this.orderService = orderService;
    }

    @PrePersist
    private void postPersist(OrderItem orderItem) {
        if(orderItem != null && orderItem.getQuantity() > 0 && orderItem.getItem() != null) {
            Double currentItemPrice = orderItem.getQuantity() * orderItem.getItem().getPrice();
            OrderDTO orderDTO = orderService.getOrder(orderItem.getOrder().getId());
            Order order = orderService.toEntity(orderDTO);
            Double currentOrderTotal = order.getTotal();
            log.debug("Back updating order total : Adding {} to current orderTotal of {} for order {}", currentItemPrice, currentOrderTotal, order.getId());
            order.setTotal(orderDTO.getTotal() + currentItemPrice);
            orderService.update(orderService.toDto(order));
        }
    }

}
