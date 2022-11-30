package com.shree.compositeKey.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shree.compositeKey.entityListener.OrderItemListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

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
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonManagedReference
    private Item item;

    @Column(name = "order_qty")
    private int quantity;

    @Override
    public int hashCode(){
        return this.getOrder() != null ? this.getOrder().hashCode(): 0  + (this.getItem() != null? this.getItem().hashCode():0);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
