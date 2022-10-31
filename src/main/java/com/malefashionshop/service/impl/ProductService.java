package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.ProductUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ProductResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.entities.*;
import com.malefashionshop.entities.enums.DeleteEnum;
import com.malefashionshop.exceptions.NotFoundException;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.mappers.ProductEntityAndProductResponseDtoMapper;
import com.malefashionshop.repository.*;
import com.malefashionshop.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductEntityAndProductResponseDtoMapper productEntityAndProductResponseDtoMapper;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {

        List<ProductEntity> listProductEntity =  this.productRepository.findAllByDeleteEnum(DeleteEnum.ACTIVE);
        List<ProductResponseDto> results = new ArrayList<>();

        listProductEntity.forEach(productEntity ->{
            ProductResponseDto customProductResponseDto = new ProductResponseDto();
            modelMapper.map(productEntity, customProductResponseDto);
            this.productEntityAndProductResponseDtoMapper.map(productEntity, customProductResponseDto);
            results.add(customProductResponseDto);
        });

        return results;
    }

    @Override
    public ProductResponseDto getProductById(Long id) {

        Optional<ProductEntity> optionalProductEntity = this.productRepository.findById(id);
        if(optionalProductEntity.isEmpty()){
            throw new NotFoundException("Product Not Found");
        }

        ProductResponseDto customProductResponseDto =  new ProductResponseDto();
        this.productEntityAndProductResponseDtoMapper.map(optionalProductEntity.get(),customProductResponseDto);
        this.modelMapper.map(optionalProductEntity.get(), customProductResponseDto);

        return customProductResponseDto;
    }

    @Override
    public ProductResponseDto createProduct(ProductUpdateDto dto) {

        ProductEntity productEntity = modelMapper.map(dto, ProductEntity.class);
        this.productEntityAndProductResponseDtoMapper.map(dto, productEntity);

        productEntity.setDeleteEnum(DeleteEnum.ACTIVE);

        //Save new productEntity to DB
        ProductEntity savedProductEntity = this.productRepository.save(productEntity);

        // Set relationship with productEntity from productImage site
        dto.getImageDetailIDs().forEach(imageDetailID ->{
            Optional<ProductImageEntity> productImageEntity = this.productImageRepository.findById(imageDetailID);
            productImageEntity.get().setProduct(savedProductEntity);
            this.productImageRepository.save(productImageEntity.get());
        });

        // Set relationship with productEntity from Tag site
        dto.getTagDetailsIDs().forEach(tagDetailsID ->{
            Optional<TagEntity> optionalTagEntity = this.tagRepository.findById(tagDetailsID);
            optionalTagEntity.get().getProducts().add(savedProductEntity);
            this.tagRepository.save(optionalTagEntity.get());
        });

        //Set attribute for ProductResponseDto
        ProductResponseDto productResponseDto = this.modelMapper.map(savedProductEntity, ProductResponseDto.class);
        this.productEntityAndProductResponseDtoMapper.map( savedProductEntity, productResponseDto);

        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductUpdateDto dto) {

        Optional<ProductEntity> optionalProductEntity = this.productRepository.findById(id);
        if(optionalProductEntity.isEmpty()){
            throw new ResourceNotFoundException("Product with ID: " + id + "can not found");
        }

        this.modelMapper.map(dto,optionalProductEntity.get());
        productEntityAndProductResponseDtoMapper.map(dto, optionalProductEntity.get());

        this.productRepository.save(optionalProductEntity.get());

        ProductResponseDto productResponseDto =  new ProductResponseDto();
        productEntityAndProductResponseDtoMapper.map(optionalProductEntity.get(), productResponseDto);
        this.modelMapper.map(optionalProductEntity.get(), productResponseDto);

        return productResponseDto;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteProduct(Long id) {

        Optional<ProductEntity> optionalProductEntity = this.productRepository.findById(id);
        if(optionalProductEntity.isEmpty() || optionalProductEntity.get().getDeleteEnum().equals(DeleteEnum.DELETED)){
            throw new ResourceNotFoundException("Product with ID: " + id + " is not found");
        }

        optionalProductEntity.get().setDeleteEnum(DeleteEnum.DELETED);

        this.productRepository.save(optionalProductEntity.get());

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(null, "Delete Success Fully!","200"));
    }



}
