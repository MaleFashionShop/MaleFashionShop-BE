package com.malefashionshop.mappers;

import com.malefashionshop.dto.request.ProductUpdateDto;
import com.malefashionshop.dto.response.ProductResponseDto;
import com.malefashionshop.entities.*;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.BrandRepository;
import com.malefashionshop.repository.CategoryRepository;
import com.malefashionshop.repository.ProductImageRepository;
import com.malefashionshop.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductEntityAndProductResponseDtoMapper {

    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private TagRepository tagRepository;

    public void map(ProductEntity productEntity, ProductResponseDto productResponseDto){

        //Set some special attributes of ProductResponseDto class

        if(productEntity.getCategory()!= null){
            productResponseDto.setCategoryName(productEntity.getCategory().getName());
        } else {
            productResponseDto.setCategoryName(null);
        }

        if(productEntity.getBrand() != null){
            productResponseDto.setBrandName(productEntity.getBrand().getName());
        } else {
            productResponseDto.setBrandName(null);
        }

        List<ProductImageEntity> images = productEntity.getImages();
        List<String> listImages = new ArrayList<String>();
        images.forEach(image -> {
            listImages.add(image.getUrl());
        });
        productResponseDto.setImageUrls(listImages);

        List<TagEntity> tags = productEntity.getTags();
        List<String> listTags= new ArrayList<String>();
        tags.forEach(tag -> {
            listTags.add(tag.getCode());
        });
        productResponseDto.setListTags(listTags);

//        return productResponseDto;
    }

    public void map(ProductUpdateDto dto, ProductEntity productEntity){

        if(dto.getFeatureImageID() != null){
            Optional<ProductImageEntity> optionalProductImageEntity =  productImageRepository.findById(dto.getFeatureImageID());
            if(optionalProductImageEntity.isEmpty()){
                throw new ResourceNotFoundException("Feature Image ID of product is not found");
            }
            productEntity.setFeatureImage(optionalProductImageEntity.get().getUrl());
        } else productEntity.setFeatureImage(null);

        if(dto.getCategoryId() != null){
            Optional<CategoryEntity> optionalCategoryEntity = this.categoryRepository.findById(dto.getCategoryId());
            if(optionalCategoryEntity.isEmpty()){
                throw new ResourceNotFoundException("Category with ID:"+ dto.getCategoryId() + "can not found");
            }
            productEntity.setCategory(optionalCategoryEntity.get());
        } else productEntity.setCategory(null);

        if(dto.getBrandId() != null){
            Optional<BrandEntity> optionalBrandEntity = this.brandRepository.findById(dto.getBrandId());
            if(optionalBrandEntity.isEmpty()){
                throw new ResourceNotFoundException("Brand with ID:"+ dto.getBrandId() + "can not found");
            }
            productEntity.setBrand(optionalBrandEntity.get());
        } else productEntity.setBrand(null);

        List<ProductImageEntity> listProductImageEntity = new ArrayList<>();
        dto.getImageDetailIDs().forEach(imageID ->{
            Optional<ProductImageEntity> productImageEntity = this.productImageRepository.findById(imageID);
            if(productImageEntity.isEmpty()){
                throw new ResourceNotFoundException("Product Image with ID:"+ imageID + "can not found");
            }
            listProductImageEntity.add(productImageEntity.get());
        });
        productEntity.setImages(listProductImageEntity);

        List<TagEntity> listTagEntity = new ArrayList<>();
        dto.getTagDetailsIDs().forEach(tagID ->{
            Optional<TagEntity> optionalTagEntity = this.tagRepository.findById(tagID);
            if(optionalTagEntity.isEmpty()){
                throw new ResourceNotFoundException("Tag with ID:"+ tagID + "can not found");
            }
            listTagEntity.add(optionalTagEntity.get());
        });
        productEntity.setTags(listTagEntity);
    }
}
