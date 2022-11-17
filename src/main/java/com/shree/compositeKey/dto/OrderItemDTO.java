package com.shree.compositeKey.dto;

import com.shree.compositeKey.entity.Customer;
import com.shree.compositeKey.entity.Item;
import com.shree.compositeKey.entity.Order;
import com.shree.compositeKey.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO implements Serializable {
    private Long id;
    private Order order;
    private Item item;
    private int quantity;
}
