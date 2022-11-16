package com.shree.compositeKey.mapper;

import com.shree.compositeKey.dto.CustomerDTO;
import com.shree.compositeKey.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
        Customer toEntity(CustomerDTO dto);
        CustomerDTO toDto(Customer dto);
}
