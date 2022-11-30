package com.shree.compositeKey.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;


@Entity(name = "orders")
@Data
public class Order {

    @Id
    @SequenceGenerator(name = "seq_order_id", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_order_id")
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_total")
    private Double total;

    @Column(name = "order_date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "order_cust_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = EAGER)
    @JsonIgnoreProperties({"order"})
    private Set<OrderItem> orderItems;

    @Override
    public int hashCode(){
        return this.getId() == null? 0 : this.getId().hashCode();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", total=" + total +
                ", date=" + date +
                ", customer=" + customer +
                '}';
    }
}
