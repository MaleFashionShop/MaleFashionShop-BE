package com.malefashionshop.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.malefashionshop.entities.*;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder=true)
public class ProductUpdateDto {
    @NotBlank(message = "Product name is required")
    private String name;

    private Long featureImageID;

    private String description;

    private Long categoryId;

    private Long brandId;

    private List<Long> imageDetailIDs;

    private List<Long> tagDetailsIDs;

}
