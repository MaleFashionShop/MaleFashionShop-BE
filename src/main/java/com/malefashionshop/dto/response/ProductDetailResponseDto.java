package com.malefashionshop.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.malefashionshop.entities.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDetailResponseDto {

    private Long id;

    private float price;

    private int quantity;

    @JsonProperty("product")
    private ProductResponseDto productResponseDto;

    @JsonProperty("size")
    private SizeResponseDto sizeResponseDto;

    @JsonProperty("color")
    private ColorResponseDto colorResponseDto;


}
