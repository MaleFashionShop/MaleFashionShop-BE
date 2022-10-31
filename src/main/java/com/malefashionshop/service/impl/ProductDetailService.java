package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.ProductDetailUpdateDto;
import com.malefashionshop.dto.request.ProductUpdateDto;
import com.malefashionshop.dto.response.ProductDetailResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.entities.ProductDetailEntity;
import com.malefashionshop.entities.enums.DeleteEnum;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.mappers.ProductDetailEntityAndProductDetailResponseDtoMapper;
import com.malefashionshop.repository.ProductDetailRepository;
import com.malefashionshop.service.IProductDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService implements IProductDetailService {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductDetailEntityAndProductDetailResponseDtoMapper productDetailMapper;

    @Override
    public List<ProductDetailResponseDto> getAllProductDetails() {
        List<ProductDetailEntity> listProductDetail = this.productDetailRepository.findAllByDeleteEnum(DeleteEnum.ACTIVE);

        List<ProductDetailResponseDto> listProductDetailResponseDto = new ArrayList<>();
        listProductDetail.forEach(productDetail -> {
            ProductDetailResponseDto productDetailResponseDto = new ProductDetailResponseDto();
            this.productDetailMapper.map(productDetail, productDetailResponseDto);
            listProductDetailResponseDto.add(productDetailResponseDto);
        });

        return listProductDetailResponseDto;
    }

    @Override
    public ProductDetailResponseDto createProductDetails(ProductDetailUpdateDto dto) {
        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
        this.productDetailMapper.map(dto, productDetailEntity);
        productDetailEntity.setDeleteEnum(DeleteEnum.ACTIVE);

        this.productDetailRepository.save(productDetailEntity);

        ProductDetailResponseDto productDetailResponseDto = new ProductDetailResponseDto();
        this.productDetailMapper.map(productDetailEntity, productDetailResponseDto);

        return productDetailResponseDto;
    }

    @Override
    public ProductDetailResponseDto updateProductDetails(Long id, ProductDetailUpdateDto productDetailUpdateDto) {
        Optional<ProductDetailEntity> optionalProductDetailEntity = this.productDetailRepository.findById(id);
        if(optionalProductDetailEntity.isEmpty() ||
                optionalProductDetailEntity.get().getDeleteEnum().equals(DeleteEnum.DELETED)){
            throw new ResourceNotFoundException("Product Detail with ID: "+id+"can be not found" );
        }
        ProductDetailEntity productDetailEntity = optionalProductDetailEntity.get();

        this.productDetailMapper.map(productDetailUpdateDto, productDetailEntity);// Map object type attribute

        this.productDetailRepository.save(optionalProductDetailEntity.get());

        ProductDetailResponseDto productDetailResponseDto = new ProductDetailResponseDto();
        this.productDetailMapper.map(productDetailEntity, productDetailResponseDto);

        return productDetailResponseDto;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteProductDetails(Long id) {
        Optional<ProductDetailEntity> optionalProductDetailEntity = this.productDetailRepository.findById(id);
        if(optionalProductDetailEntity.isEmpty() ||
                optionalProductDetailEntity.get().getDeleteEnum().equals(DeleteEnum.DELETED)){
            throw new ResourceNotFoundException("Product Detail with ID: "+id+"can be not found" );
        }
        ProductDetailEntity productDetailEntity = optionalProductDetailEntity.get();
        productDetailEntity.setDeleteEnum(DeleteEnum.DELETED);

        this.productDetailRepository.save(productDetailEntity);

        ProductDetailResponseDto productDetailResponseDto = new ProductDetailResponseDto();
        this.productDetailMapper.map(productDetailEntity, productDetailResponseDto);


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Success Fully!","200"));
    }
}
