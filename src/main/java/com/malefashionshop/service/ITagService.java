package com.malefashionshop.service;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.TagUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.TagResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITagService {

    public List<TagResponseDto> getAllTags();

    TagResponseDto createTag(TagUpdateDto dto);

    ResponseEntity<ResponseDto> deleteTag(Long id);

    TagResponseDto updateTag(Long id, TagUpdateDto dto);

//    public TagResponseDto getTagById(Long id);
//
//    TagResponseDto createTag(TagResponseDto dto);
//
//    TagResponseDto updateTag(Long id, TagResponseDto dto);
//
//    void deleteTag(Long id);
}
