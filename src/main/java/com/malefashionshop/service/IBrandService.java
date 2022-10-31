package com.malefashionshop.service;

import com.malefashionshop.dto.request.BrandUpdateDto;
import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.response.BrandResponseDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBrandService {
    BrandResponseDto createBrand(BrandUpdateDto dto);

    BrandResponseDto updateBrand(Long id, BrandUpdateDto dto);

    List<BrandResponseDto> getAllBrands();

    ResponseEntity<ResponseDto> deleteBrand(Long id);
}
