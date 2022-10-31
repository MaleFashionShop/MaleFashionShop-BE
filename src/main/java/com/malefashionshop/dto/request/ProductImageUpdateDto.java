package com.malefashionshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ProductImageUpdateDto {
    @NotBlank(message = "Image's name is required")
    private String name;

    @NotBlank(message = "Image's url is required")
    private String url;

    private Long productID;


}
