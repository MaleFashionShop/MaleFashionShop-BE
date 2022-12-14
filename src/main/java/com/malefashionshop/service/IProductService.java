package com.malefashionshop.service;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.ProductUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ProductResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    List<ProductResponseDto> getAllProducts();

    List<ProductResponseDto> getProductByCategory(Long id);

    ProductResponseDto getProductById(Long id);

    ProductResponseDto createProduct(ProductUpdateDto dto);

    ResponseEntity<ResponseDto> deleteProduct(Long id);

    ProductResponseDto updateProduct(Long id, ProductUpdateDto dto);

    List<ProductResponseDto> getProductByBrand(Long brandID);


//    void deleteProduct(Long id);
}
