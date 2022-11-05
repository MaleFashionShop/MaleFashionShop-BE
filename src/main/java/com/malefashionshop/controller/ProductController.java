package com.malefashionshop.controller;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.ProductUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ProductResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.service.impl.CategoryService;
import com.malefashionshop.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponseDto> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id){
        return this.productService.getProductById(id);
    }

    @GetMapping("/category/{id}")
    public List<ProductResponseDto> getProductByCategory(@PathVariable("id") Long categoryID){
        return this.productService.getProductByCategory(categoryID);
    }

    @GetMapping("/brand/{id}")
    public List<ProductResponseDto> getProductByBrand(@PathVariable("id") Long brandID){
        return this.productService.getProductByBrand(brandID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto createProduct(@RequestBody ProductUpdateDto dto){
        return this.productService.createProduct(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable Long id) {
        return this.productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") Long id, @RequestBody ProductUpdateDto dto){
        return this.productService.updateProduct(id, dto);
    }


}
