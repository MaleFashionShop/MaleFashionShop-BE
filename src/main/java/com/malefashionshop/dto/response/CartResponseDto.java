package com.malefashionshop.dto.response;

import com.malefashionshop.entities.CustomerEntity;
import com.malefashionshop.entities.ProductDetailEntity;
import lombok.Data;

@Data
public class CartResponseDto {
    private Long id;
    private int quantity;
    private float price;
    private Long customerID;
    private ProductDetailResponseDto productDetail;
}
