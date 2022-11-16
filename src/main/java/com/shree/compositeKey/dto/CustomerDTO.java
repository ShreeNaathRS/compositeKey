package com.shree.compositeKey.dto;

import com.shree.compositeKey.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
public class CustomerDTO implements Serializable {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String city;
    private String pincode;

    public CustomerDTO(){}
    public CustomerDTO(Long id, String name, String phoneNumber, String email, String addressLine1, String addressLine2, String state, String city, String pincode) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.state = state;
        this.city = city;
        this.pincode = pincode;
    }
}
