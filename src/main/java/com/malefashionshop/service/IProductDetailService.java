package com.malefashionshop.service;

import com.malefashionshop.dto.request.ProductDetailUpdateDto;
import com.malefashionshop.dto.request.ProductUpdateDto;
import com.malefashionshop.dto.response.ProductDetailResponseDto;
import com.malefashionshop.dto.response.ProductResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductDetailService {
    List<ProductDetailResponseDto> getAllProductDetails();

    ProductDetailResponseDto createProductDetails(ProductDetailUpdateDto dto);

    ProductDetailResponseDto updateProductDetails(Long id, ProductDetailUpdateDto dto);

    ResponseEntity<ResponseDto> deleteProductDetails(Long id);
}
