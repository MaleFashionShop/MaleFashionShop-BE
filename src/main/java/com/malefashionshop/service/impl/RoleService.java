package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.RoleUpdateDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.RoleResponseDto;
import com.malefashionshop.entities.RoleEntity;
import com.malefashionshop.entities.SizeEntity;
import com.malefashionshop.entities.enums.ERole;
import com.malefashionshop.exceptions.DataConstrainConflictException;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.mappers.RoleEntityAndRoleResponseDtoMapper;
import com.malefashionshop.repository.RoleRepository;
import com.malefashionshop.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleEntityAndRoleResponseDtoMapper roleEntityAndRoleResponseDtoMapper;

    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<RoleEntity> listRoleEntity = this.roleRepository.findAll();
        List<RoleResponseDto> listRoleResponseDto = new ArrayList<>();
        listRoleEntity.forEach(roleEntity->{
            RoleResponseDto roleResponseDto = new RoleResponseDto();
            this.roleEntityAndRoleResponseDtoMapper.map(roleEntity, roleResponseDto);
            listRoleResponseDto.add(roleResponseDto);
        });
        return listRoleResponseDto;
    }

    @Override
    public RoleResponseDto createRole(RoleUpdateDto dto) {

//        RoleEntity existRoleEntity = this.roleRepository.findByName(dto.getName());
//        if(existRoleEntity != null){
//            throw new DataConstrainConflictException("Size name " +dto.getName()+ " is already exist");
//        }

        RoleEntity roleEntity = new RoleEntity();
        this.roleEntityAndRoleResponseDtoMapper.map(dto, roleEntity);

        this.roleRepository.save(roleEntity);

        RoleResponseDto roleResponseDto = new RoleResponseDto();
        this.roleEntityAndRoleResponseDtoMapper.map(roleEntity, roleResponseDto);

        return roleResponseDto;
    }

    @Override
    public RoleResponseDto updateRole(Long id, RoleUpdateDto dto) {
        Optional<RoleEntity> optionalRoleEntity = this.roleRepository.findById(id);
        if(optionalRoleEntity.isEmpty()){
            throw new ResourceNotFoundException("Role with ID: " +id+ "can be not found");
        }

       this.roleEntityAndRoleResponseDtoMapper.map(dto, optionalRoleEntity.get());

        this.roleRepository.save(optionalRoleEntity.get());

        RoleResponseDto roleResponseDto = new RoleResponseDto();
        this.roleEntityAndRoleResponseDtoMapper.map(optionalRoleEntity.get(), roleResponseDto);

        return roleResponseDto;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteRole(Long id) {
        Optional<RoleEntity> optionalRoleEntity = this.roleRepository.findById(id);
        if(optionalRoleEntity.isEmpty()){
            throw new ResourceNotFoundException("Role with ID: " +id+ "can be not found");
        }

        this.roleRepository.delete(optionalRoleEntity.get());

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(null, "Delete Success Fully!","200"));
    }
}
