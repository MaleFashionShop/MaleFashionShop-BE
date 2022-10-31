package com.malefashionshop.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder(toBuilder=true)
public class TagUpdateDto {
    @NotBlank(message = "Tag code is required")
    private String code;

    private String name;


}
