package com.shree.compositeKey.service;

import com.shree.compositeKey.dto.CustomerDTO;
import com.shree.compositeKey.entity.Customer;
import com.shree.compositeKey.mapper.CustomerMapper;
import com.shree.compositeKey.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepo;

    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO getCustomer(Long id) {
        Customer customer;
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
            return customerMapper.toDto(customer);
        }
        else {
            return null;
        }
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO dto) throws Exception {
        Customer customer;
        if (dto.getId() == null) {
            customer = customerMapper.toEntity(dto);
            customer = customerRepo.save(customer);
        } else {
            log.error("id cannot be present for new entry");
            throw new Exception("id cannot be present for new entry");
        }
        return customerMapper.toDto(customer);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            customerRepo.deleteById(id);
        } else {
            log.error("Customer with id {} not present", id);
            throw new Exception("Customer with id " + id + " not present");
        }
    }

    @Override
    public CustomerDTO findByPhoneNumber(String phoneNumber) {
        Customer customer = customerRepo.findByPhoneNumber(phoneNumber);
        return customerMapper.toDto(customer);
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        return customerMapper.toEntity(customerDTO);
    }


}
