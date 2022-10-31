package com.malefashionshop.dto.response;

import com.malefashionshop.entities.EntityKey.OrderItemKey;
import com.malefashionshop.entities.OrderEntity;
import com.malefashionshop.entities.ProductDetailEntity;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class OrderItemResponseDto {
    private OrderItemKey id;

    private int quantity;

//    private OrderResponseDto orderResponseDto;

    private ProductDetailResponseDto productDetailResponseDto;
}
