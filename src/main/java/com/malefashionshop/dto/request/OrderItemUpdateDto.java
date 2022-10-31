package com.malefashionshop.dto.request;

import com.malefashionshop.dto.response.OrderResponseDto;
import com.malefashionshop.dto.response.ProductDetailResponseDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder(toBuilder=true)
public class OrderItemUpdateDto {

    @NotBlank(message = "OrderItem must belong to a order")
    private Long orderId;

    @NotBlank(message = "OrderItem must belong to a product detail")
    private Long productDetailID;

    private int quantity;



}
