package com.shree.compositeKey.dto;

import com.shree.compositeKey.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
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
    private LocalDate warrantyEndDate;
    private LocalDate manufacturedDate;
}
