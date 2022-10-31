package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.CustomerUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.CustomerResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.CustomerEntity;
import com.malefashionshop.entities.enums.DeleteEnum;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.mappers.CustomerEntityAndCustomerResponseDtoMapper;
import com.malefashionshop.repository.CategoryRepository;
import com.malefashionshop.repository.CustomerRepository;
import com.malefashionshop.service.ICategoryService;
import com.malefashionshop.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerEntityAndCustomerResponseDtoMapper customerEntityAndResponseDtoMapper;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerEntityAndCustomerResponseDtoMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerEntityAndResponseDtoMapper = customerMapper;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<CustomerEntity> listCustomerEntity = customerRepository.findAllByDeleteEnum(DeleteEnum.ACTIVE);
        return customerEntityAndResponseDtoMapper.toCustomerResponseDtoList(listCustomerEntity);
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerUpdateDto dto) {

        CustomerEntity customerEntity = customerEntityAndResponseDtoMapper.map(dto);

        customerEntity.setDeleteEnum(DeleteEnum.ACTIVE);

        customerRepository.save(customerEntity);

        CustomerResponseDto customerResponseDto = customerEntityAndResponseDtoMapper.map(customerEntity);

        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerUpdateDto dto) {
        Optional<CustomerEntity> optionalCustomerEntity = this.customerRepository.findById(id);
        if(optionalCustomerEntity.isEmpty()){
            throw new ResourceNotFoundException("Customer with ID: "+id+" can be not found");
        }
        this.customerEntityAndResponseDtoMapper.map(dto, optionalCustomerEntity.get());

        this.customerRepository.save(optionalCustomerEntity.get());

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
//        this.customerEntityAndResponseDtoMapper.map(optionalCustomerEntity.get(), customerResponseDto);
        customerResponseDto = customerEntityAndResponseDtoMapper.map(optionalCustomerEntity.get());

        return customerResponseDto;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCustomer(Long id) {
        Optional<CustomerEntity> optionalCustomerEntity = this.customerRepository.findById(id);
        if(optionalCustomerEntity.isEmpty()){
            throw new ResourceNotFoundException("Customer with ID: "+id+" can be not found");
        }

        optionalCustomerEntity.get().setDeleteEnum(DeleteEnum.DELETED);

        this.customerRepository.save(optionalCustomerEntity.get());

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(null, "Delete Success Fully!","200"));
    }
}
