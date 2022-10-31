package com.malefashionshop.controller;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.CustomerUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.CustomerResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.service.impl.CategoryService;
import com.malefashionshop.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    List<CustomerResponseDto> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerResponseDto createCustomer(@Valid @RequestBody CustomerUpdateDto dto){
        return this.customerService.createCustomer(dto);
    }

    @PutMapping("/{id}")
    CustomerResponseDto updateCustomer(@PathVariable("id") Long id,@Valid @RequestBody CustomerUpdateDto dto ){
        return this.customerService.updateCustomer(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCustomer(@PathVariable Long id) {
        return this.customerService.deleteCustomer(id);
    }

}
