package com.malefashionshop.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.malefashionshop.dto.response.ColorResponseDto;
import com.malefashionshop.dto.response.ProductResponseDto;
import com.malefashionshop.dto.response.SizeResponseDto;
import com.malefashionshop.entities.*;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder(toBuilder=true)
public class ProductDetailUpdateDto {

    private float price;

    private int quantity;

//    @NotBlank(message = "Product Detail must belong to a Product")
    private Long productID;

    private Long sizeID;

    private Long colorID;



}
