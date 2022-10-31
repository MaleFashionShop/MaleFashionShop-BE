package com.malefashionshop.controller;

import com.malefashionshop.dto.request.ProductDetailUpdateDto;
import com.malefashionshop.dto.request.ProductUpdateDto;
import com.malefashionshop.dto.response.ProductDetailResponseDto;
import com.malefashionshop.dto.response.ProductResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.service.impl.ProductDetailService;
import com.malefashionshop.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productDetails")
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping
    public List<ProductDetailResponseDto> getAllProductDetails(){
        return this.productDetailService.getAllProductDetails();
    }

    @PostMapping
    public ProductDetailResponseDto createProductDetails( @RequestBody ProductDetailUpdateDto dto){
        return this.productDetailService.createProductDetails(dto);
    }

    @PutMapping("/{id}")
    public ProductDetailResponseDto updateProductDetails(@PathVariable(name = "id") Long id,
                                                         @RequestBody ProductDetailUpdateDto dto){
        return this.productDetailService.updateProductDetails(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProductDetails(@PathVariable(name = "id") Long id){
        return this.productDetailService.deleteProductDetails(id);
    }


}
