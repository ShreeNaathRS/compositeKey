package com.shree.compositeKey.dto;

import com.shree.compositeKey.entity.Customer;
import com.shree.compositeKey.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private Long id;
    private Double total;
    private LocalDate date;
    private Customer customer;
    private Set<OrderItem> orderItems;

    public OrderDTO(Long id, Double total, LocalDate date, Customer customer) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.customer = customer;
    }
}
