package com.malefashionshop.controller;

import com.malefashionshop.dto.request.TagUpdateDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.TagResponseDto;
import com.malefashionshop.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    List<TagResponseDto> getAllTags(){
        return this.tagService.getAllTags();
    }

    @PostMapping
    TagResponseDto createTag( @RequestBody TagUpdateDto dto){
        return this.tagService.createTag(dto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto> deleteTag(@PathVariable("id") Long id ){
        return this.tagService.deleteTag(id);
    }

    @PutMapping("/{id}")
    TagResponseDto updateTag(@PathVariable("id") Long id,@RequestBody TagUpdateDto dto){
        return this.tagService.updateTag(id,dto);
    }
}
