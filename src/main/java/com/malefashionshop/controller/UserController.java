package com.malefashionshop.controller;

import com.malefashionshop.dto.request.UserUpdateDto;
import com.malefashionshop.dto.response.UserResponseDto;
import com.malefashionshop.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    List<UserResponseDto> getUsers(){
        return this.userService.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserResponseDto createUser(@Valid @RequestBody UserUpdateDto dto){
        return this.userService.createUser(dto);
    }



}
