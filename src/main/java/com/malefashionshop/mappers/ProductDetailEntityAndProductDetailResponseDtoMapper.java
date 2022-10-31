package com.malefashionshop.mappers;

import com.malefashionshop.dto.request.ProductDetailUpdateDto;
import com.malefashionshop.dto.response.ColorResponseDto;
import com.malefashionshop.dto.response.ProductDetailResponseDto;
import com.malefashionshop.dto.response.ProductResponseDto;
import com.malefashionshop.dto.response.SizeResponseDto;
import com.malefashionshop.entities.ColorEntity;
import com.malefashionshop.entities.ProductDetailEntity;
import com.malefashionshop.entities.ProductEntity;
import com.malefashionshop.entities.SizeEntity;
import com.malefashionshop.exceptions.DataConstrainConflictException;
import com.malefashionshop.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductDetailEntityAndProductDetailResponseDtoMapper {
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductEntityAndProductResponseDtoMapper productEntityAndDtoMapper;

    public void map(ProductDetailEntity productDetailEntity, ProductDetailResponseDto productDetailResponseDto) {
        BeanUtils.copyProperties(productDetailEntity, productDetailResponseDto);
        //Set some object type attributes of ProductDetailResponseDto class
        if (productDetailEntity.getProduct() == null) {
            throw new DataConstrainConflictException("Product Detail must be long to a Product");
        } else {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            this.modelMapper.map(productDetailEntity.getProduct(), productResponseDto);
            this.productEntityAndDtoMapper.map(productDetailEntity.getProduct(), productResponseDto);
            productDetailResponseDto.setProductResponseDto(productResponseDto);
        }

        if (productDetailEntity.getSize() != null) {
            SizeResponseDto sizeResponseDto = new SizeResponseDto();
            this.modelMapper.map(productDetailEntity.getSize(), sizeResponseDto);
            productDetailResponseDto.setSizeResponseDto(sizeResponseDto);
        } else {
            productDetailResponseDto.setSizeResponseDto(null);
        }

        if (productDetailEntity.getColor() != null) {
            ColorResponseDto colorResponseDto = new ColorResponseDto();
            this.modelMapper.map(productDetailEntity.getColor(), colorResponseDto);
            productDetailResponseDto.setColorResponseDto(colorResponseDto);
        } else {
            productDetailResponseDto.setColorResponseDto(null);
        }
    }

    public void map(ProductDetailUpdateDto productDetailUpdateDto, ProductDetailEntity productDetailEntity) {
        BeanUtils.copyProperties(productDetailUpdateDto, productDetailEntity); // Just copy not object type attribute
        if (productDetailUpdateDto.getProductID() == null) {
            throw new DataConstrainConflictException("Product Detail must be long to a Product");
        } else {
            Optional<ProductEntity> optionalProductEntity = this.productRepository.findById(productDetailUpdateDto.getProductID());
            productDetailEntity.setProduct(optionalProductEntity.get());
        }


        Optional<SizeEntity> optionalSizeEntity = this.sizeRepository.findById(productDetailUpdateDto.getSizeID());
        productDetailEntity.setSize(optionalSizeEntity.get());


        Optional<ColorEntity> optionalColorEntity = this.colorRepository.findById(productDetailUpdateDto.getColorID());
        productDetailEntity.setColor(optionalColorEntity.get());

    }
}
