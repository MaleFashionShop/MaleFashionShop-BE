package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.TagUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.dto.response.TagResponseDto;
import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.TagEntity;
import com.malefashionshop.entities.enums.DeleteEnum;
import com.malefashionshop.exceptions.DataConstrainConflictException;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.CategoryRepository;
import com.malefashionshop.repository.TagRepository;
import com.malefashionshop.service.ICategoryService;
import com.malefashionshop.service.ITagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService implements ITagService {

    @Autowired
    private TagRepository tagRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<TagResponseDto> getAllTags() {

        List<TagResponseDto> listTagResponseDto = new ArrayList<>();

        List<TagEntity> listTagEntity = this.tagRepository.findAll();

        listTagEntity.forEach(tagEntity ->{
            TagResponseDto tagResponseDto = new TagResponseDto();
            this.modelMapper.map(tagEntity,tagResponseDto);
            listTagResponseDto.add(tagResponseDto);
        });

        return listTagResponseDto;
    }

    @Override
    public TagResponseDto createTag(TagUpdateDto dto) {

        if(this.tagRepository.findByCode(dto.getCode()) != null){
            throw new DataConstrainConflictException("Tag code: "+dto.getCode() +" is already exist");
        }

        TagEntity tagEntity = modelMapper.map(dto, TagEntity.class);
        this.tagRepository.save(tagEntity);

        return modelMapper.map(tagEntity,TagResponseDto.class);
    }

    @Override
    public TagResponseDto updateTag(Long id, TagUpdateDto dto) {

        Optional<TagEntity> optionalTagEntity = this.tagRepository.findById(id);
        if(optionalTagEntity.isEmpty()){
            throw new ResourceNotFoundException("Tag with ID:"+ id + "can not found");
        }
        this.modelMapper.map(dto,optionalTagEntity.get());

        this.tagRepository.save(optionalTagEntity.get());

        return this.modelMapper.map(optionalTagEntity.get(),TagResponseDto.class);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteTag(Long id) {

        Optional<TagEntity> optionalTagEntity =  this.tagRepository.findById(id);

        if(optionalTagEntity.isEmpty()){
            throw new ResourceNotFoundException("Tag with ID:"+id+" Not Found");
        }
        this.tagRepository.delete(optionalTagEntity.get());

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Success Fully!","200"));
    }


}
