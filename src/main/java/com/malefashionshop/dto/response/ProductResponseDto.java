package com.malefashionshop.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.malefashionshop.entities.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponseDto {

    private Long id;

    private String name;

    private String featureImage;

    private String description;

    private String categoryName;

    private String brandName;

    List<String> imageUrls = new ArrayList<>();

    List<String> listTags = new ArrayList<>();

}
