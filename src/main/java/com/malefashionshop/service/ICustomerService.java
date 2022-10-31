package com.malefashionshop.service;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.CustomerUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.CustomerResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICustomerService {
    public List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto createCustomer(CustomerUpdateDto dto);

    CustomerResponseDto updateCustomer(Long id, CustomerUpdateDto dto);

    ResponseEntity<ResponseDto> deleteCustomer(Long id);
}
