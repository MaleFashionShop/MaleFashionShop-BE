package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.UserUpdateDto;
import com.malefashionshop.dto.response.UserResponseDto;
import com.malefashionshop.entities.UserEntity;
import com.malefashionshop.mappers.UserEntityAndUserResponseDtoMapper;
import com.malefashionshop.repository.UserRepository;
import com.malefashionshop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserEntityAndUserResponseDtoMapper userEntityAndResponseDtoMapper;

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> listUserEntity  = this.userRepository.findAll();
        List<UserResponseDto> listUserResponseDto = new ArrayList<>();
        listUserEntity.forEach(userEntity->{
            UserResponseDto userResponseDto = new UserResponseDto();
            this.userEntityAndResponseDtoMapper.map(userEntity, userResponseDto);
            listUserResponseDto.add(userResponseDto);
        });
        return listUserResponseDto;
    }

    @Override
    public UserResponseDto createUser(UserUpdateDto dto) {
        UserEntity userEntity = new UserEntity();
        this.userEntityAndResponseDtoMapper.map(dto,userEntity);

        this.userRepository.save(userEntity);

        UserResponseDto userResponseDto = new UserResponseDto();
        this.userEntityAndResponseDtoMapper.map(userEntity,userResponseDto);

        return userResponseDto;
    }
}
