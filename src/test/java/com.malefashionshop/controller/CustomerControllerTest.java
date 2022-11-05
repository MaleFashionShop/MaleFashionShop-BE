package com.malefashionshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malefashionshop.dto.request.CustomerUpdateDto;
import com.malefashionshop.dto.response.CustomerResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.entities.CustomerEntity;
import com.malefashionshop.entities.RoleEntity;
import com.malefashionshop.exceptions.NotFoundException;
import com.malefashionshop.mappers.CustomerEntityAndCustomerResponseDtoMapper;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.malefashionshop.repository.CustomerRepository;
import com.malefashionshop.service.impl.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    CustomerEntityAndCustomerResponseDtoMapper customerMapper;
    

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();
    CustomerResponseDto customerResponseDto;
    CustomerUpdateDto customerUpdateDto;


    @BeforeEach
    void setUp() {
        customerResponseDto = new CustomerResponseDto(1L,"Long","abc@gamil.com"
                ,"0123","HCM", "ROLE_ADMIN"  );

        customerUpdateDto = CustomerUpdateDto.builder().name("Long").email("abc@gamil.com")
                .phoneNumber("0123").address("HCM").roleID(1L).build();
    }

    @Test
    @WithMockUser(value="admin",roles={"ADMIN"})
    void getAllCustomers_ShouldReturnListCustomer_WhenDataSuccess() throws Exception {
        List<CustomerResponseDto> listCustomerResponseDto = new ArrayList<>();
        listCustomerResponseDto.add(customerResponseDto);

        when(customerService.getAllCustomers()).thenReturn(listCustomerResponseDto);

        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(value="admin",roles={"ADMIN"})
    void createCustomer_ShouldReturnCustomer_WhenDataSuccess() throws Exception {
        when(customerService.createCustomer(customerUpdateDto)).thenReturn(customerResponseDto);
        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(customerUpdateDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(value="admin",roles={"ADMIN"})
    void updateCustomer_ShouldThrowException_WhenDataNotValid() throws Exception {
        Long id = 999L;
        when(customerService.updateCustomer(id, customerUpdateDto)).thenThrow(NotFoundException.class);
        mockMvc.perform(put("/customer/{id}",id).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(customerUpdateDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value="admin",roles={"ADMIN"})
    void updateCustomer_ShouldReturnCustomerResponseDto_WhenDataValid() throws Exception {
        Long id = 1L;
        when(customerService.updateCustomer(id, customerUpdateDto)).thenReturn(customerResponseDto);
        mockMvc.perform(put("/customer/{id}",id).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(customerUpdateDto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value="admin",roles={"ADMIN"})
    void deleteCustomer_ShouldThrowException_WhenDataNotValid() throws Exception {
        Long id = 999L;
        when(customerService.deleteCustomer(id)).thenThrow(NotFoundException.class);
        mockMvc.perform(delete("/customer/{id}",id).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(customerUpdateDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(value="admin",roles={"ADMIN"})
    void deleteCustomer_ShouldReturnSuccessResponse_WhenDataValid() throws Exception {
        Long id = 1L;
        when(customerService.deleteCustomer(id)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(null, "Delete Success Fully!","200")));

        mockMvc.perform(delete("/customer/{id}",id).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(customerUpdateDto)))
                .andExpect(status().isOk());
    }
}