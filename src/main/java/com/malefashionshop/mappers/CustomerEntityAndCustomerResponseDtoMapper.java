package com.malefashionshop.mappers;

import com.malefashionshop.dto.request.CustomerUpdateDto;
import com.malefashionshop.dto.response.CustomerResponseDto;
import com.malefashionshop.entities.CustomerEntity;
import com.malefashionshop.entities.RoleEntity;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerEntityAndCustomerResponseDtoMapper {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<CustomerResponseDto> toCustomerResponseDtoList(List<CustomerEntity> customerEntities){
        return customerEntities.isEmpty() ? Collections.emptyList() : customerEntities.stream().map(customerEntity -> {
            var customerResponseDto =  modelMapper.map(customerEntity, CustomerResponseDto.class);
            var roleName = !Objects.isNull(customerEntity.getRole())
                    && !Objects.isNull(customerEntity.getRole().getName())
                    ?  customerEntity.getRole().getName() : null;
            customerResponseDto.setRoleName(String.valueOf(roleName));
            return customerResponseDto;
        }).collect(Collectors.toList());
    }

    public void map(CustomerEntity customerEntity, CustomerResponseDto customerResponseDto){
        BeanUtils.copyProperties(customerEntity, customerResponseDto);
        if(customerEntity.getRole() == null){
            customerResponseDto.setRoleName(null);
        } else{
            customerResponseDto.setRoleName(customerEntity.getRole().getName().name());
        }
    }

    public void map(CustomerUpdateDto customerUpdateDto, CustomerEntity customerEntity){
        BeanUtils.copyProperties(customerUpdateDto, customerEntity);

        if(customerUpdateDto.getRoleID() == null){
            customerEntity.setRole(null);
        } else{
            Optional<RoleEntity> optionalRoleEntity = this.roleRepository.findById(customerUpdateDto.getRoleID());
            if(optionalRoleEntity.isEmpty()){
                throw new ResourceNotFoundException("Role with ID: "+customerUpdateDto.getRoleID()+"can be not found");
            }
            customerEntity.setRole(optionalRoleEntity.get());
        }

    }

    public CustomerResponseDto map(CustomerEntity customerEntity) {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();

        BeanUtils.copyProperties(customerEntity, customerResponseDto);
        if(customerEntity.getRole() == null){
            customerResponseDto.setRoleName(null);
        } else{
            customerResponseDto.setRoleName(customerEntity.getRole().getName().name());
        }

        return customerResponseDto;
    }

    public CustomerEntity map(CustomerUpdateDto customerUpdateDto) {
        CustomerEntity customerEntity = new CustomerEntity();

        BeanUtils.copyProperties(customerUpdateDto, customerEntity);

        if(customerUpdateDto.getRoleID() == null){
            customerEntity.setRole(null);
        } else{
            Optional<RoleEntity> optionalRoleEntity = this.roleRepository.findById(customerUpdateDto.getRoleID());
            if(optionalRoleEntity.isEmpty()){
                throw new ResourceNotFoundException("Role with ID: "+customerUpdateDto.getRoleID()+"can be not found");
            }
            customerEntity.setRole(optionalRoleEntity.get());
        }

        return customerEntity;
    }
}
