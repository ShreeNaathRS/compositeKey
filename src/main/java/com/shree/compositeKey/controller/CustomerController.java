package com.shree.compositeKey.controller;

import java.net.URI;

import com.shree.compositeKey.dto.CustomerDTO;
import com.shree.compositeKey.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{id}")
    ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        CustomerDTO customerDto = customerService.getCustomer(id);
        return ResponseEntity.ok(customerDto);
    }

    @PostMapping("/customer/save")
    ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO dto) throws Exception {
        CustomerDTO customerDto = customerService.saveCustomer(dto);
        return ResponseEntity.created(new URI("/customer/save")).body(customerDto);
    }

    @DeleteMapping("/customer/delete/{id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable Long id) throws Exception {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
