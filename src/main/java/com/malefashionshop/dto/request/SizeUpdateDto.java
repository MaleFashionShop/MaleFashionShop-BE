package com.malefashionshop.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder(toBuilder=true)
public class SizeUpdateDto {
    @NotBlank(message = "Size name is required")
    private String name;

    private String temp;

}
