package com.malefashionshop.service;

import com.malefashionshop.dto.request.UserUpdateDto;
import com.malefashionshop.dto.response.UserResponseDto;

import java.util.List;

public interface IUserService {
    public List<UserResponseDto> getAllUsers();

    UserResponseDto createUser(UserUpdateDto dto);
}
