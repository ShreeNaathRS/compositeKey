package com.shree.compositeKey.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shree.compositeKey.enums.Category;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "item")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uq_item_catg_name", columnNames = {"item_category", "item_name", "item_company", "item_model", "item_color"})
})
@Data
public class Item {

    @Id
    @SequenceGenerator(name = "seq_item_id", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_item_id")
    @Column(name = "item_id")
    private Long id;

    @Enumerated(STRING)
    @Column(name = "item_category", nullable = false)
    private Category category;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "item_company", nullable = false)
    private String company;

    @Column(name = "item_model", nullable = false)
    private String model;

    @Column(name = "item_price", nullable = false)
    private Double price;

    @Column(name = "item_weight")
    private Double weight;

    @Column(name = "item_color")
    private String color;

    @Column(name = "item_warranty", nullable = false)
    private Boolean warranty;

    @Column(name = "item_warranty_end_dt")
    private LocalDate warrantyEndDate;

    @Column(name = "item_manufactured_dt")
    private LocalDate manufacturedDate;

    @OneToMany(mappedBy = "item")
    @JsonBackReference
    private Set<OrderItem> orderItems;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                ", warranty=" + warranty +
                ", warrantyEndDate=" + warrantyEndDate +
                ", manufacturedDate=" + manufacturedDate +
                '}';
    }
}
