package com.shree.compositeKey.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
