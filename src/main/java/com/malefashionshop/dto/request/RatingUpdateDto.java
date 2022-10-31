package com.malefashionshop.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder(toBuilder=true)
public class RatingUpdateDto {
    @NotNull(message= "Customer ID  is required")
    private Long customerID;
    @NotNull(message= "Product ID  is required")
    private Long productID;

    private String comment;

    private int score;



}
