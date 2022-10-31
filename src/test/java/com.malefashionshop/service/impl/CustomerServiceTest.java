package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.CustomerUpdateDto;
import com.malefashionshop.dto.response.CustomerResponseDto;
import com.malefashionshop.entities.CustomerEntity;
import com.malefashionshop.entities.enums.DeleteEnum;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.mappers.CustomerEntityAndCustomerResponseDtoMapper;
import com.malefashionshop.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    CustomerService customerService;
    CustomerEntity customerEntity;
    CustomerEntityAndCustomerResponseDtoMapper customerMapper;
    CustomerResponseDto expectedCustomer;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerEntity = new CustomerEntity();

        customerRepository = mock(CustomerRepository.class);
        customerMapper = mock(CustomerEntityAndCustomerResponseDtoMapper.class);

        customerService = new CustomerService(customerRepository, customerMapper);

        expectedCustomer = CustomerResponseDto.builder().id(1L).name("name").email("email").phoneNumber("phoneNumber")
                .address("address").roleName("roleName").build();
    }

    @Test
    void getAllCustomers_ShouldReturnListCustomerResponseDto_WhenDataValid() {
        List<CustomerEntity> customerEntities = mock(List.class);
        List<CustomerResponseDto> customerResponseDtoes = mock(List.class);

        when(customerRepository.findAllByDeleteEnum(DeleteEnum.ACTIVE)).thenReturn(customerEntities);
        when(customerMapper.toCustomerResponseDtoList(customerEntities)).thenReturn(customerResponseDtoes);

        List<CustomerResponseDto> results = customerService.getAllCustomers();

        assertEquals(customerResponseDtoes, results);

    }

    @Test
    void createCustomer_ShouldReturnCustomerResponseDto_WhenDataValid() {
        CustomerUpdateDto customerUpdateDto = CustomerUpdateDto.builder().name("name").email("email")
                .phoneNumber("phoneNumber").address("address").roleID(1L).build();

        customerEntity = mock(CustomerEntity.class);
        when(customerMapper.map(customerUpdateDto)).thenReturn(customerEntity);

        when(customerMapper.map(customerEntity)).thenReturn(expectedCustomer);

        CustomerResponseDto result = customerService.createCustomer(customerUpdateDto);

        assertEquals(expectedCustomer, result);

    }

    @Test
    void updateCustomer_ShouldThrowException_WhenDataNotValid() {
        String expected = "Customer with ID: 999 can be not found";
        CustomerUpdateDto customerUpdateDto = CustomerUpdateDto.builder().name("name").email("email")
                .phoneNumber("phoneNumber").address("address").roleID(1L).build();

        ResourceNotFoundException notFoundException = Assertions.assertThrows(
                ResourceNotFoundException.class,()->customerService.updateCustomer(999L, customerUpdateDto));
        Assertions.assertEquals(expected, notFoundException.getMessage());
    }

    @Test
    void updateCustomer_ShouldReturnCustomerResponseDto_WhenDataValid(){
        Long customerId = 1L;
        customerEntity = mock(CustomerEntity.class);

        CustomerUpdateDto customerUpdateDto = CustomerUpdateDto.builder().name("nameUpdate").email("emailUpdate")
                .phoneNumber("phoneNumberUpdate").address("addressUpdate").roleID(1L).build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));

        when(customerMapper.map(Optional.of(customerEntity).get())).thenReturn(expectedCustomer);

        CustomerResponseDto result = customerService.updateCustomer(customerId, customerUpdateDto);

        assertEquals(expectedCustomer, result);
    }

    @Test
    void deleteCustomer_ShouldThrowResourceNotFoundException_WhenDataNotValid() {
        Long customerId = 999L;
        String expected = "Customer with ID: "+customerId+" can be not found";

        ResourceNotFoundException notFoundException = Assertions.assertThrows(
                ResourceNotFoundException.class,()->customerService.deleteCustomer(customerId));

        Assertions.assertEquals(expected, notFoundException.getMessage());
    }

    @Test
    void deleteCustomer_ShouldReturnDeleteResponseDto_WhenDataValid() {
        Long customerId = 999L;

        customerEntity = mock(CustomerEntity.class);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));

        customerService.deleteCustomer(customerId);
    }
}