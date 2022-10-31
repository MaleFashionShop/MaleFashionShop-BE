package com.malefashionshop.controller;

import com.malefashionshop.dto.request.RoleUpdateDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.RoleResponseDto;
import com.malefashionshop.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    List<RoleResponseDto> getRoles(){
        return this.roleService.getAllRoles();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RoleResponseDto createRole(@Valid @RequestBody RoleUpdateDto dto){
        return this.roleService.createRole(dto);
    }

    @PutMapping("/{id}")
    RoleResponseDto updateRole(@PathVariable("id") Long id,@RequestBody RoleUpdateDto dto ){
        return this.roleService.updateRole(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteRole(@PathVariable Long id) {
        return this.roleService.deleteRole(id);
    }

}
