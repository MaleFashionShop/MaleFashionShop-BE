package com.malefashionshop.service;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoryService {
    public List<CategoryResponseDto> getAllCategories();
    public CategoryResponseDto getCategoryById(Long id);

    CategoryResponseDto createCategory(CategoryUpdateDto dto);

    CategoryResponseDto updateCategory(Long id, CategoryUpdateDto dto);

    ResponseEntity<ResponseDto> deleteCategory(Long id);
}
