package com.malefashionshop.controller;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.SizeUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.SizeResponseDto;
import com.malefashionshop.service.impl.CategoryService;
import com.malefashionshop.service.impl.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping
    List<SizeResponseDto> getAllSizes(){
        return this.sizeService.getAllSizes();
    }

    @PostMapping
    SizeResponseDto createSize(@RequestBody SizeUpdateDto sizeUpdateDto){
        return this.sizeService.createSize(sizeUpdateDto);
    }

    @PutMapping("/{id}")
    SizeResponseDto updateSize(@PathVariable(name = "id") Long id,@RequestBody SizeUpdateDto sizeUpdateDto){
        return this.sizeService.updateSize(id, sizeUpdateDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto> deleteSize(@PathVariable(name = "id") Long id){
        return this.sizeService.deleteSize(id);
    }

}
