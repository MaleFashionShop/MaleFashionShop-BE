package com.malefashionshop.service;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.RatingUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.RatingResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRatingService {
    public List<RatingResponseDto> getAllRatings();

    List<RatingResponseDto> getAllRatingByProductID(Long productID);

    RatingResponseDto createRating(RatingUpdateDto dto);

    ResponseEntity<ResponseDto> deleteRating(Long customerID, Long productID);
}
