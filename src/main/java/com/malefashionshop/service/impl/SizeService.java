package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.SizeUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.SizeResponseDto;
import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.SizeEntity;
import com.malefashionshop.exceptions.DataConstrainConflictException;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.CategoryRepository;
import com.malefashionshop.repository.SizeRepository;
import com.malefashionshop.service.ICategoryService;
import com.malefashionshop.service.ISizeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SizeService implements ISizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<SizeResponseDto> getAllSizes() {
        List<SizeEntity> listSizeEntity = this.sizeRepository.findAll();

        List<SizeResponseDto> listSizeResponseDto = new ArrayList<>();
        listSizeEntity.forEach(sizeEntity -> {
            listSizeResponseDto.add(this.modelMapper.map(sizeEntity, SizeResponseDto.class));
        });

        return listSizeResponseDto;
    }

    @Override
    public SizeResponseDto createSize(SizeUpdateDto sizeUpdateDto) {
        SizeEntity sizeEntity = this.sizeRepository.findByName(sizeUpdateDto.getName());
        if(sizeEntity != null){
            throw new DataConstrainConflictException("Size name " +sizeUpdateDto.getName()+ " is already exist");
        }

        SizeEntity size = this.modelMapper.map(sizeUpdateDto, SizeEntity.class);
        this.sizeRepository.save(size);

        return this.modelMapper.map(size, SizeResponseDto.class);
    }

    @Override
    public SizeResponseDto updateSize(Long id, SizeUpdateDto sizeUpdateDto) {
        Optional<SizeEntity> optionalSizeEntity = this.sizeRepository.findById(id);
        if(optionalSizeEntity.isEmpty()){
            throw new DataConstrainConflictException("Size with ID " +id+ "can be not found");
        }

        SizeEntity existSizeName = this.sizeRepository.findByName(sizeUpdateDto.getName());
        if(existSizeName != null){
            throw new DataConstrainConflictException("Size name " +sizeUpdateDto.getName()+ " is already exist");
        }

        this.modelMapper.map(sizeUpdateDto, optionalSizeEntity.get());
        this.sizeRepository.save(optionalSizeEntity.get());
        
        return this.modelMapper.map(optionalSizeEntity.get(), SizeResponseDto.class);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteSize(Long id) {
        Optional<SizeEntity> optionalSizeEntity =  this.sizeRepository.findById(id);
        if(optionalSizeEntity.isEmpty()){
            throw new ResourceNotFoundException("Size with ID: " +id+" can be not found");
        }
        this.sizeRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Success Fully!","200"));
    }
}
