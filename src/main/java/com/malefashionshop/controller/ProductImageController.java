package com.malefashionshop.controller;

import com.malefashionshop.dto.request.ProductImageUpdateDto;
import com.malefashionshop.dto.response.ProductImageResponseDto;
import com.malefashionshop.dto.response.UploadImageResponseDto;
import com.malefashionshop.entities.ProductImageEntity;
import com.malefashionshop.service.impl.ImagesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
public class ProductImageController {
    @Autowired
    ImagesStorageService imagesStorageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UploadImageResponseDto> saveImages(
            @RequestBody List<ProductImageUpdateDto> listProductImageUpdateDto) {
        return imagesStorageService.createImages(listProductImageUpdateDto);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductImageResponseDto>> getImagesByProductID(@PathVariable Long productId) {
        return imagesStorageService.getAllImagesByProductID(productId);
    }

    @PutMapping("/{productImageID}")
    public ResponseEntity<ProductImageResponseDto> updateImages(@PathVariable("productImageID") Long productImageID,
                                                                @RequestBody ProductImageUpdateDto productImageUpdateDto){
        return this.imagesStorageService.updateImage(productImageID, productImageUpdateDto);
    }

}
