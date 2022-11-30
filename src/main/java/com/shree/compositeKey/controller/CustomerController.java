package com.shree.compositeKey.controller;

import com.shree.compositeKey.dto.CustomerDTO;
import com.shree.compositeKey.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
