package com.malefashionshop.mappers;

import com.malefashionshop.dto.request.CartUpdateDto;
import com.malefashionshop.dto.request.OrderUpdateDto;
import com.malefashionshop.dto.response.CartResponseDto;
import com.malefashionshop.dto.response.OrderItemResponseDto;
import com.malefashionshop.dto.response.OrderResponseDto;
import com.malefashionshop.dto.response.ProductDetailResponseDto;
import com.malefashionshop.entities.CartEntity;
import com.malefashionshop.entities.CustomerEntity;
import com.malefashionshop.entities.OrderEntity;
import com.malefashionshop.entities.ProductDetailEntity;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.CustomerRepository;
import com.malefashionshop.repository.OrderItemRepository;
import com.malefashionshop.repository.ProductDetailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartEntityAndCartResponseDtoMapper {

    @Autowired
    ProductDetailEntityAndProductDetailResponseDtoMapper productDetailEntityAndResponseDtoMapper;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;

    public void map(CartEntity cartEntity, CartResponseDto cartResponseDto){
        BeanUtils.copyProperties(cartEntity, cartResponseDto);

        if(cartEntity.getCustomer() != null){
            cartResponseDto.setCustomerID(cartEntity.getCustomer().getId());
        }

        if(cartEntity.getProductDetail() != null){
            ProductDetailResponseDto productDetailResponseDto = new ProductDetailResponseDto();
            this.productDetailEntityAndResponseDtoMapper.map(cartEntity.getProductDetail(),productDetailResponseDto);
            cartResponseDto.setProductDetail(productDetailResponseDto);
        }
        var price = cartResponseDto.getProductDetail().getPrice()*cartResponseDto.getQuantity();
        cartResponseDto.setPrice(price);



    }

    public void map(CartUpdateDto cartUpdateDto, CartEntity cartEntity){
        BeanUtils.copyProperties(cartUpdateDto,cartEntity);

        if(cartUpdateDto.getCustomerID() != null){
            Optional<CustomerEntity> optionalCustomerEntity = this.customerRepository.findById(
                    cartUpdateDto.getCustomerID());
            if(optionalCustomerEntity.isEmpty()){
                throw new ResourceNotFoundException("Customer with ID: "+cartUpdateDto.getCustomerID()+
                        "can be not found");
            }
            cartEntity.setCustomer(optionalCustomerEntity.get());
        }

        if(cartUpdateDto.getProductDetailID() != null){
            Optional<ProductDetailEntity> optionalProductDetailEntity = this.productDetailRepository.findById(
                    cartUpdateDto.getProductDetailID());
            if(optionalProductDetailEntity.isEmpty()){
                throw new ResourceNotFoundException("Product Detail with ID: "+cartUpdateDto.getProductDetailID()+
                        "can be not found");
            }
            cartEntity.setProductDetail(optionalProductDetailEntity.get());
        }

    }
}
