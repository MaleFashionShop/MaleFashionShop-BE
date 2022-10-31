package com.malefashionshop.mappers;

import com.malefashionshop.dto.request.UserUpdateDto;
import com.malefashionshop.dto.response.UserResponseDto;
import com.malefashionshop.entities.UserEntity;
import com.malefashionshop.entities.RoleEntity;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserEntityAndUserResponseDtoMapper {
    @Autowired
    RoleRepository roleRepository;

    public void map(UserEntity userEntity, UserResponseDto userResponseDto){
        BeanUtils.copyProperties(userEntity, userResponseDto);
        if(userEntity.getRole() != null){
            userResponseDto.setRoleID(userEntity.getRole().getId());
        } else{
            userResponseDto.setRoleID(null);
        }
    }

    public void map(UserUpdateDto userUpdateDto, UserEntity userEntity){
        BeanUtils.copyProperties(userUpdateDto, userEntity);

        Optional<RoleEntity> optionalRoleEntity = this.roleRepository.findById(userUpdateDto.getRoleID());
        if(optionalRoleEntity.isEmpty()){
            throw new ResourceNotFoundException("Role with ID: " +userUpdateDto.getRoleID()+ "can be not found");
        }
        userEntity.setRole(optionalRoleEntity.get()); // Tạm thời
    }
}
