package com.malefashionshop.mappers;

import com.malefashionshop.dto.request.OrderItemUpdateDto;
import com.malefashionshop.dto.request.ProductUpdateDto;
import com.malefashionshop.dto.response.OrderItemResponseDto;
import com.malefashionshop.dto.response.ProductDetailResponseDto;
import com.malefashionshop.dto.response.ProductResponseDto;
import com.malefashionshop.entities.*;
import com.malefashionshop.entities.EntityKey.OrderItemKey;
import com.malefashionshop.exceptions.DataConstrainConflictException;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderItemEntityAndOrderItemResponseDtoMapper {

    @Autowired
    private ProductDetailEntityAndProductDetailResponseDtoMapper productDetailEntityAndResponseDtoMapper;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private OrderRepository orderRepository;

    public void map(OrderItemEntity orderItemEntity, OrderItemResponseDto orderItemResponseDto){
        ProductDetailResponseDto productDetailResponseDto = new ProductDetailResponseDto();

        BeanUtils.copyProperties(orderItemEntity, orderItemResponseDto);

        orderItemResponseDto.getId().setOrderId(orderItemEntity.getId().getOrderId());
        orderItemResponseDto.getId().setProductDetailID(orderItemEntity.getId().getProductDetailID());

        this.productDetailEntityAndResponseDtoMapper.map(orderItemEntity.getProductDetail(),productDetailResponseDto);
        orderItemResponseDto.setProductDetailResponseDto(productDetailResponseDto);
    }

    public void map(OrderItemUpdateDto orderItemUpdateDto, OrderItemEntity orderItemEntity){
        BeanUtils.copyProperties(orderItemUpdateDto, orderItemEntity);

        if(orderItemUpdateDto.getProductDetailID() == null){
            throw new DataConstrainConflictException("OrderItem must belong to a ProductDetail");
        }
        if(orderItemUpdateDto.getOrderId() == null){
            throw new DataConstrainConflictException("OrderItem must belong to a Order");
        }

        OrderItemKey orderItemKey = new OrderItemKey(orderItemUpdateDto.getOrderId(),
                orderItemUpdateDto.getProductDetailID());
        orderItemEntity.setId(orderItemKey);


        Optional<ProductDetailEntity> optionalProductDetailEntity =
                this.productDetailRepository.findById(orderItemUpdateDto.getProductDetailID());
        if(optionalProductDetailEntity.isEmpty()){
            throw new ResourceNotFoundException("Product Detail with ID: "+
                    orderItemUpdateDto.getProductDetailID()+ " can be not found");
        }
        orderItemEntity.setProductDetail(optionalProductDetailEntity.get());

        Optional<OrderEntity> optionalOrderEntity =
                this.orderRepository.findById(orderItemUpdateDto.getOrderId());
        if(optionalOrderEntity.isEmpty()){
            throw new ResourceNotFoundException("Order with ID: "+
                    orderItemUpdateDto.getProductDetailID()+ " can be not found");
        }
        orderItemEntity.setOrder(optionalOrderEntity.get());

    }
}
