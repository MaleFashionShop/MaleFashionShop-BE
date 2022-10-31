package com.malefashionshop.mappers;

import com.malefashionshop.dto.request.OrderUpdateDto;
import com.malefashionshop.dto.request.RoleUpdateDto;
import com.malefashionshop.dto.response.CustomerResponseDto;
import com.malefashionshop.dto.response.OrderItemResponseDto;
import com.malefashionshop.dto.response.OrderResponseDto;
import com.malefashionshop.dto.response.RoleResponseDto;
import com.malefashionshop.entities.CustomerEntity;
import com.malefashionshop.entities.OrderEntity;
import com.malefashionshop.entities.RoleEntity;
import com.malefashionshop.entities.enums.ERole;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.CustomerRepository;
import com.malefashionshop.repository.OrderItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RoleEntityAndRoleResponseDtoMapper {

    public void map(RoleEntity roleEntity, RoleResponseDto roleResponseDto){
        BeanUtils.copyProperties(roleEntity,roleResponseDto);
        roleResponseDto.setName(roleEntity.getName().toString());
    }

    public void map(RoleUpdateDto roleUpdateDto, RoleEntity roleEntity){
        BeanUtils.copyProperties(roleUpdateDto,roleEntity);
        ERole temp = ERole.fromText(roleUpdateDto.getName());
        if(temp== null) throw new ResourceNotFoundException("Role Name is not exist");
        roleEntity.setName(temp);



    }
}
