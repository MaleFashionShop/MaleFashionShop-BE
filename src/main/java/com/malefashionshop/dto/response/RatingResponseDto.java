package com.malefashionshop.dto.response;

import com.malefashionshop.entities.EntityKey.RatingKey;
import lombok.Data;

import javax.persistence.Column;

@Data
public class RatingResponseDto {
    private RatingKey id;

    private String comment;

    private int score;
}
