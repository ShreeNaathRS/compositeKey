package com.shree.compositeKey.entity;

import lombok.Data;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.*;
import java.util.List;

@Entity(name = "customer")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uq_cust_ph_no", columnNames = {"cust_ph_no"})
})
@Data
public class Customer {

    @Id
    @SequenceGenerator(name = "seq_cust_id", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_cust_id")
    @Column(name = "cust_id")
    private Long id;

    @Column(name = "cust_name", nullable = false)
    private String name;

    @Column(name = "cust_ph_no")
    private String phoneNumber;

    @Column(name = "cust_email")
    private String email;

    @Column(name = "cust_address_line_1", nullable = false)
    private String addressLine1;

    @Column(name = "cust_address_line_2")
    private String addressLine2;

    @Column(name = "cust_state", nullable = false)
    private String state;

    @Column(name = "cust_city", nullable = false)
    private String city;

    @Column(name = "cust_pincode", nullable = false)
    private String pincode;

}
