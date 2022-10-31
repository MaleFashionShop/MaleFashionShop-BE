package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.CategoryRepository;
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
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryResponseDto> getAllCategories() {

        List<CategoryResponseDto> results = new ArrayList<>();

        List<CategoryEntity> categories = this.categoryRepository.findAll();

        categories.forEach((category) -> {
            CategoryResponseDto categoryDTO = modelMapper.map(category, CategoryResponseDto.class);
            results.add(categoryDTO);
        });

        return results;
    }

    public CategoryResponseDto getCategoryById(Long id) {

        Optional<CategoryEntity> categoryEntityOptinal = this.categoryRepository.findById(id);

        if(categoryEntityOptinal.isEmpty()){
            throw new ResourceNotFoundException("Category Not Found");
        }

        CategoryResponseDto categoryDTO = modelMapper.map(categoryEntityOptinal.get(), CategoryResponseDto.class);

        return categoryDTO;

    }

    @Override
    public CategoryResponseDto createCategory(CategoryUpdateDto dto) {

        CategoryEntity category = modelMapper.map(dto, CategoryEntity.class);

        CategoryEntity savedCategory = this.categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryUpdateDto dto) {

        Optional<CategoryEntity> categoryOptional = this.categoryRepository.findById(id);
        if(categoryOptional.isEmpty()){
            throw new ResourceNotFoundException("Category Not Found");
        }

        CategoryEntity category = categoryOptional.get();
        modelMapper.map(dto, category);

        category = this.categoryRepository.save(category);

        return modelMapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCategory(Long id) {

        Optional<CategoryEntity> categoryEntity =  this.categoryRepository.findById(id);
        if(categoryEntity.isEmpty()){
            throw new ResourceNotFoundException("Category Not Found");
        }

        this.categoryRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Success Fully!","200"));
    }

}
