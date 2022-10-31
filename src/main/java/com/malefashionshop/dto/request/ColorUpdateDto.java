package com.malefashionshop.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder(toBuilder=true)
public class ColorUpdateDto {
    @NotBlank(message = "Color name is required")
    private String name;

    @NotBlank(message = "Color code is required")
    private String code;

}
