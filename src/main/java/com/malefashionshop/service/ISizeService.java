package com.malefashionshop.service;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.SizeUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.SizeResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISizeService {
    public List<SizeResponseDto> getAllSizes();

    SizeResponseDto createSize(SizeUpdateDto sizeUpdateDto);

    SizeResponseDto updateSize(Long id, SizeUpdateDto sizeUpdateDto);

    ResponseEntity<ResponseDto> deleteSize(Long id);
}
