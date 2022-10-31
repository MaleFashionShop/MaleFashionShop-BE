package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.ColorUpdateDto;
import com.malefashionshop.dto.request.SizeUpdateDto;
import com.malefashionshop.dto.response.ColorResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.SizeResponseDto;
import com.malefashionshop.entities.ColorEntity;
import com.malefashionshop.entities.SizeEntity;
import com.malefashionshop.exceptions.DataConstrainConflictException;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.ColorRepository;
import com.malefashionshop.repository.SizeRepository;
import com.malefashionshop.service.IColorService;
import com.malefashionshop.service.ISizeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorService implements IColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ColorResponseDto> getAllColors() {
        List<ColorEntity> listColorEntity= this.colorRepository.findAll();

        List<ColorResponseDto> listColorResponseDto = new ArrayList<>();
        listColorEntity.forEach(colorEntity -> {
            listColorResponseDto.add(this.modelMapper.map(colorEntity, ColorResponseDto.class));
        });

        return listColorResponseDto;
    }

    @Override
    public ColorResponseDto createColor(ColorUpdateDto colorUpdateDto) {

        ColorEntity color = this.modelMapper.map(colorUpdateDto, ColorEntity.class);

        this.colorRepository.save(color);

        return this.modelMapper.map(color, ColorResponseDto.class);
    }

    @Override
    public ColorResponseDto updateColor(Long id, ColorUpdateDto colorUpdateDto) {
        Optional<ColorEntity> optionalColorEntity= this.colorRepository.findById(id);
        if(optionalColorEntity.isEmpty()){
            throw new DataConstrainConflictException("Color with ID " +id+ "can be not found");
        }

        this.modelMapper.map(colorUpdateDto, optionalColorEntity.get());
        this.colorRepository.save(optionalColorEntity.get());

        return this.modelMapper.map(optionalColorEntity.get(), ColorResponseDto.class);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteColor(Long id) {
        Optional<ColorEntity> optionalColorEntity =  this.colorRepository.findById(id);
        if(optionalColorEntity.isEmpty()){
            throw new ResourceNotFoundException("Size with ID: " +id+" can be not found");
        }
        this.colorRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Success Fully!","200"));

    }
}
