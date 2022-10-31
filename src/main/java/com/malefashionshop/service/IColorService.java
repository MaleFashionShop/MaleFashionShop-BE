package com.malefashionshop.service;

import com.malefashionshop.dto.request.ColorUpdateDto;
import com.malefashionshop.dto.request.SizeUpdateDto;
import com.malefashionshop.dto.response.ColorResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.SizeResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IColorService {
    public List<ColorResponseDto> getAllColors();

    ColorResponseDto createColor(ColorUpdateDto colorUpdateDto);

    ColorResponseDto updateColor(Long id, ColorUpdateDto colorUpdateDto);

    ResponseEntity<ResponseDto> deleteColor(Long id);

//    SizeResponseDto createSize(SizeUpdateDto sizeUpdateDto);
//
//    SizeResponseDto updateSize(Long id, SizeUpdateDto sizeUpdateDto);
//
//    ResponseEntity<ResponseDto> deleteSize(Long id);
}
