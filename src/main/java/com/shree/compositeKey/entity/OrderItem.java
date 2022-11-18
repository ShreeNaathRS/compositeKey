package com.shree.compositeKey.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shree.compositeKey.entityListener.OrderItemListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.CascadeType.LOCK;
import static org.hibernate.annotations.CascadeType.MERGE;

@Entity(name = "order_items")
@EntityListeners(OrderItemListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    @SequenceGenerator(name = "seq_order_item_id", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_order_item_id")
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnoreProperties("orderItems")
    private Item item;

    @Column(name = "order_qty")
    private int quantity;

    @Override
    public int hashCode(){
        return this.getOrder() != null ? this.getOrder().hashCode(): 0  + (this.getItem() != null? this.getItem().hashCode():0);
    }

}
