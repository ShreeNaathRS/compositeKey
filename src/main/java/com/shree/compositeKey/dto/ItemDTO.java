package com.shree.compositeKey.dto;

import com.shree.compositeKey.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO implements Serializable {
    private Long id;
    private Category category;
    private String name;
    private String company;
    private String model;
    private Double price;
    private Double weight;
    private String color;
    private Boolean warranty;
    private LocalDateTime warrantyEndDate;
    private LocalDateTime manufacturedDate;
}
