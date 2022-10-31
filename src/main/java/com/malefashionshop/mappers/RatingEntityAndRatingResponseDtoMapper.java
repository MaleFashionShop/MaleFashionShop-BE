package com.malefashionshop.mappers;

import com.malefashionshop.dto.request.OrderUpdateDto;
import com.malefashionshop.dto.request.RatingUpdateDto;
import com.malefashionshop.dto.response.OrderItemResponseDto;
import com.malefashionshop.dto.response.OrderResponseDto;
import com.malefashionshop.dto.response.RatingResponseDto;
import com.malefashionshop.entities.CustomerEntity;
import com.malefashionshop.entities.EntityKey.RatingKey;
import com.malefashionshop.entities.OrderEntity;
import com.malefashionshop.entities.ProductEntity;
import com.malefashionshop.entities.RatingEntity;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.CustomerRepository;
import com.malefashionshop.repository.OrderItemRepository;
import com.malefashionshop.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RatingEntityAndRatingResponseDtoMapper {
    @Autowired
    OrderItemEntityAndOrderItemResponseDtoMapper orderItemEntityAndResponseDtoMapper;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    public void map(RatingEntity ratingEntity, RatingResponseDto ratingResponseDto){
        BeanUtils.copyProperties(ratingEntity,ratingResponseDto);

        ratingResponseDto.getId().setCustomerID(ratingEntity.getId().getCustomerID());
        ratingResponseDto.getId().setProductID(ratingEntity.getId().getProductID());
    }

    public void map(RatingUpdateDto ratingUpdateDto, RatingEntity ratingEntity){
        BeanUtils.copyProperties(ratingUpdateDto,ratingEntity);

        RatingKey ratingKey = new RatingKey(ratingUpdateDto.getCustomerID(),ratingUpdateDto.getProductID());
        ratingEntity.setId(ratingKey);

        Optional<CustomerEntity> optionalCustomerEntity = this.customerRepository.findById(ratingUpdateDto.getCustomerID());
        if(optionalCustomerEntity.isEmpty()){
            throw new ResourceNotFoundException("Customer with ID: "+ratingUpdateDto.getCustomerID()+" can be not found");
        }
        ratingEntity.setCustomer(optionalCustomerEntity.get());

        Optional<ProductEntity> optionalProductEntity = this.productRepository.findById(ratingUpdateDto.getProductID());
        if(optionalProductEntity.isEmpty()){
            throw new ResourceNotFoundException("Product with ID: "+ratingUpdateDto.getProductID()+" can be not found");
        }
        ratingEntity.setProduct(optionalProductEntity.get());

    }
}
