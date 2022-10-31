package com.malefashionshop.controller;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    List<CategoryResponseDto> getAllCategories(){
        return this.categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    CategoryResponseDto getCategoryById(@PathVariable("id") Long id){
        return this.categoryService.getCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CategoryResponseDto createCategory(@Valid @RequestBody CategoryUpdateDto dto){
        return this.categoryService.createCategory(dto);
    }

    @PutMapping("/{id}")
    CategoryResponseDto updateCategory(@PathVariable("id") Long id,@Valid @RequestBody CategoryUpdateDto dto ){
        return this.categoryService.updateCategory(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable Long id) {
        return this.categoryService.deleteCategory(id);
    }

}
