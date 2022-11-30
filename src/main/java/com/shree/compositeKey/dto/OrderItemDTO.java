package com.shree.compositeKey.dto;

import com.shree.compositeKey.entity.Item;
import com.shree.compositeKey.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO implements Serializable {
    private Long id;
    private Order order;
    private Item item;
    private int quantity;
}
