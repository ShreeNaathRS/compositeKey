package com.shree.compositeKey.service;

import com.shree.compositeKey.dto.CustomerDTO;
import com.shree.compositeKey.entity.Customer;

public interface CustomerService {
    CustomerDTO getCustomer(Long id);
    CustomerDTO saveCustomer(CustomerDTO dto) throws Exception;
    void deleteCustomer(Long id) throws Exception;
    CustomerDTO findByPhoneNumber(String phoneNumber);
    Customer toEntity(CustomerDTO customerDTO);
}
