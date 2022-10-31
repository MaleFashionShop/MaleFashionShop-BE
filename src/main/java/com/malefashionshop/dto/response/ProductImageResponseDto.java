package com.malefashionshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageResponseDto {
    private Long id;
    private String name;
    private String url;
    private Long productID;
}
