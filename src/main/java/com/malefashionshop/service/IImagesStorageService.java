package com.malefashionshop.service;

import com.malefashionshop.dto.request.ProductImageUpdateDto;
import com.malefashionshop.dto.response.ProductImageResponseDto;
//import com.malefashionshop.dto.response.UploadImageResponseDto;
import com.malefashionshop.dto.response.UploadImageResponseDto;
import com.malefashionshop.entities.ProductImageEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;


public interface IImagesStorageService {
    void init();

    ResponseEntity<List<ProductImageResponseDto>> getAllImagesByProductID(Long productId);

    ResponseEntity<UploadImageResponseDto> createImages(List<ProductImageUpdateDto> listProductImageUpdateDto);

//    ResponseEntity<ProductImageResponseDto> updateImages(ProductImageUpdateDto productImageUpdateDto);

    ResponseEntity<ProductImageResponseDto> updateImage(Long productImageID, ProductImageUpdateDto productImageUpdateDto);
}
