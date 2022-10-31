package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.BrandUpdateDto;
import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.response.BrandResponseDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.entities.BrandEntity;
import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.BrandRepository;
import com.malefashionshop.repository.CategoryRepository;
import com.malefashionshop.service.IBrandService;
import com.malefashionshop.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BrandResponseDto> getAllBrands() {
        List<BrandEntity> listBrandEntity = this.brandRepository.findAll();
        List<BrandResponseDto> listBrandResponseDto = new ArrayList<>();
        listBrandEntity.forEach(brandEntity->{
            listBrandResponseDto.add(this.modelMapper.map(brandEntity, BrandResponseDto.class));
        });
        return listBrandResponseDto;
    }

    @Override
    public BrandResponseDto createBrand(BrandUpdateDto dto) {

        BrandEntity brandEntity = modelMapper.map(dto, BrandEntity.class);
        BrandEntity savedBrandEntity = this.brandRepository.save(brandEntity);

        return modelMapper.map(savedBrandEntity, BrandResponseDto.class);
    }

    @Override
    public BrandResponseDto updateBrand(Long id, BrandUpdateDto dto) {

        Optional<BrandEntity> optionalBrandEntity = this.brandRepository.findById(id);
        if(optionalBrandEntity.isEmpty()){
            throw new ResourceNotFoundException("Brand with ID: " + id+ " cnan not found");
        }

        this.modelMapper.map(dto, optionalBrandEntity.get());

        this.brandRepository.save(optionalBrandEntity.get());

        return this.modelMapper.map(optionalBrandEntity.get(), BrandResponseDto.class);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteBrand(Long id) {
        this.brandRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Success Fully!","200"));
    }

}
