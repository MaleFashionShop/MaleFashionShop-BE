package com.malefashionshop.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder(toBuilder=true)
public class CartUpdateDto {

    private int quantity;

    private Long customerID;

    private Long productDetailID;


}
