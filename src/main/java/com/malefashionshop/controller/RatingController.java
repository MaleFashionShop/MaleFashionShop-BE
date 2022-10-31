package com.malefashionshop.controller;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.RatingUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.RatingResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.service.impl.CategoryService;
import com.malefashionshop.service.impl.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping
    List<RatingResponseDto> getAllRatings(){
        return this.ratingService.getAllRatings();
    }

    @GetMapping("/product/{productID}")
    List<RatingResponseDto> getAllRatingByProductID(@PathVariable("productID") Long productID){
        return this.ratingService.getAllRatingByProductID(productID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RatingResponseDto createRating(@Valid @RequestBody RatingUpdateDto dto){
        return this.ratingService.createRating(dto);
    }

    @DeleteMapping("/{customerID}/{productID}")
    public ResponseEntity<ResponseDto> deleteRating(@PathVariable("customerID") Long customerID,
                                                    @PathVariable("productID") Long productID ) {
        return this.ratingService.deleteRating(customerID, productID);
    }

}
