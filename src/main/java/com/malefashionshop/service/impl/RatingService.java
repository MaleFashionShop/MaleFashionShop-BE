package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.RatingUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.RatingResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.EntityKey.RatingKey;
import com.malefashionshop.entities.RatingEntity;
import com.malefashionshop.exceptions.DataConstrainConflictException;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.mappers.RatingEntityAndRatingResponseDtoMapper;
import com.malefashionshop.repository.CategoryRepository;
import com.malefashionshop.repository.RatingRepository;
import com.malefashionshop.service.ICategoryService;
import com.malefashionshop.service.IRatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements IRatingService {
    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    RatingEntityAndRatingResponseDtoMapper ratingEntityAndResponseDtoMapper;


    @Override
    public List<RatingResponseDto> getAllRatings() {
        List<RatingEntity> listRatingEntity = this.ratingRepository.findAll();
        List<RatingResponseDto> listRatingResponseDto = new ArrayList<>();

        listRatingEntity.forEach(ratingEntity->{
            RatingResponseDto ratingResponseDto = new RatingResponseDto();
            this.ratingEntityAndResponseDtoMapper.map(ratingEntity,ratingResponseDto);
            listRatingResponseDto.add(ratingResponseDto);
        });

        return listRatingResponseDto;
    }

    @Override
    public List<RatingResponseDto> getAllRatingByProductID(Long productID) {
        List<RatingEntity> listRatingEntity = this.ratingRepository.findAllByProductId(productID);
        List<RatingResponseDto> listRatingResponseDto = new ArrayList<>();

        listRatingEntity.forEach(ratingEntity->{
            RatingResponseDto ratingResponseDto = new RatingResponseDto();
            this.ratingEntityAndResponseDtoMapper.map(ratingEntity,ratingResponseDto);
            listRatingResponseDto.add(ratingResponseDto);
        });

        return listRatingResponseDto;
    }

    @Override
    public RatingResponseDto createRating(RatingUpdateDto dto) {
        RatingKey key = new RatingKey(dto.getCustomerID(), dto.getProductID());
        Optional<RatingEntity> optionalRatingEntity =  this.ratingRepository.findById(key);
        if(optionalRatingEntity.isPresent()){
            throw new DataConstrainConflictException("Rating with Customer ID: "+dto.getCustomerID()+
                    " in Product: "+ dto.getProductID()+" is already exist. Please just update it !");
        }

        RatingEntity ratingEntity= new RatingEntity();
        this.ratingEntityAndResponseDtoMapper.map(dto, ratingEntity);

        this.ratingRepository.save(ratingEntity);

        RatingResponseDto ratingResponseDto = new RatingResponseDto();
        this.ratingEntityAndResponseDtoMapper.map(ratingEntity, ratingResponseDto);

        return ratingResponseDto;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteRating(Long customerID, Long productID) {
        RatingKey ratingKey = new RatingKey(customerID, productID);
        Optional<RatingEntity> optionalRatingEntity =  this.ratingRepository.findById(ratingKey);
        if(optionalRatingEntity.isEmpty()){
            throw new ResourceNotFoundException("Category Not Found");
        }

        this.ratingRepository.deleteById(ratingKey);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Success Fully!","200"));

    }
}
