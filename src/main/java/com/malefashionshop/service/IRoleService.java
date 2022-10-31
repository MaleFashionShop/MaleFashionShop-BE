package com.malefashionshop.service;

import com.malefashionshop.dto.request.RoleUpdateDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.RoleResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRoleService {
    List<RoleResponseDto> getAllRoles();

    RoleResponseDto createRole(RoleUpdateDto dto);

    RoleResponseDto updateRole(Long id, RoleUpdateDto dto);

    ResponseEntity<ResponseDto> deleteRole(Long id);
}
