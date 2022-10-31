package com.malefashionshop.controller;

import com.malefashionshop.dto.request.ColorUpdateDto;
import com.malefashionshop.dto.response.ColorResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.service.impl.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    List<ColorResponseDto> getAllColors(){
        return this.colorService.getAllColors();
    }

    @PostMapping
    ColorResponseDto createColor(@RequestBody ColorUpdateDto colorUpdateDto){
        return this.colorService.createColor(colorUpdateDto);
    }

    @PutMapping("/{id}")
    ColorResponseDto updateColor(@PathVariable(name = "id") Long id,@RequestBody ColorUpdateDto colorUpdateDto){
        return this.colorService.updateColor(id, colorUpdateDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto> deleteColor(@PathVariable(name = "id") Long id){
        return this.colorService.deleteColor(id);
    }

}
