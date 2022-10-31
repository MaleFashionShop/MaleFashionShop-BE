package com.malefashionshop.service.impl;

import com.malefashionshop.controller.ProductImageController;
import com.malefashionshop.dto.request.ProductImageUpdateDto;
import com.malefashionshop.dto.response.ProductImageResponseDto;
import com.malefashionshop.dto.response.UploadImageResponseDto;
import com.malefashionshop.entities.ProductEntity;
import com.malefashionshop.entities.ProductImageEntity;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.ProductImageRepository;
import com.malefashionshop.repository.ProductRepository;
import com.malefashionshop.service.IImagesStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImagesStorageService implements IImagesStorageService {

    private Path root = Paths.get("uploads/productImages");

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ServletContext context;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public ResponseEntity<List<ProductImageResponseDto>> getAllImagesByProductID(Long productId) {

        List<ProductImageEntity> productImageEnties = new ArrayList<>();
        List<ProductImageResponseDto> fileInfos = new ArrayList<>();

        System.out.println(this.root);
        try {
            productImageEnties = productImageRepository.findAllByProductId(productId);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Could not load the files!");
        }

        productImageEnties.forEach(productImageEntity -> {
            ProductImageResponseDto productImageResponseDto = new ProductImageResponseDto();
            BeanUtils.copyProperties(productImageEntity, productImageResponseDto);
            if(productImageEntity.getProduct() != null){
                productImageResponseDto.setProductID(productImageEntity.getProduct().getId());
            } else{
                productImageResponseDto.setProductID(null);
            }
            fileInfos.add(productImageResponseDto);

        });
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @Override
    public ResponseEntity<UploadImageResponseDto> createImages(List<ProductImageUpdateDto> listProductImageUpdateDto) {
        List<ProductImageResponseDto> listProductImageResponseDto= new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        listProductImageUpdateDto.forEach(productImageUpdateDto -> {
            ProductImageEntity productImageEntity = new ProductImageEntity();

            BeanUtils.copyProperties(productImageUpdateDto, productImageEntity);

            if(productImageUpdateDto.getProductID() != null){
                var productID = productImageUpdateDto.getProductID();

                Optional<ProductEntity> optionalProductEntity = this.productRepository.findById(productID);
                if(optionalProductEntity.isEmpty()){
                    throw new ResourceNotFoundException("Product with ID: "+productID+ " can be not found");
                }
                productImageEntity.setProduct(optionalProductEntity.get());
            } else{
                productImageEntity.setProduct(null);
            }

            this.productImageRepository.save(productImageEntity);

            ProductImageResponseDto productImageResponseDto = new ProductImageResponseDto();

            BeanUtils.copyProperties(productImageEntity, productImageResponseDto);
            if(productImageEntity.getProduct()!= null){
                productImageResponseDto.setProductID(productImageEntity.getProduct().getId());
            } else {
                productImageResponseDto.setProductID(null);
            }

            listProductImageResponseDto.add(productImageResponseDto);

        });

            return ResponseEntity.status(HttpStatus.OK).body(new UploadImageResponseDto(listProductImageResponseDto));
    }

    @Override
    public ResponseEntity<ProductImageResponseDto> updateImage(Long productImageID, ProductImageUpdateDto productImageUpdateDto) {
        Optional<ProductImageEntity> optionalProductImageEntity = this.productImageRepository.findById(productImageID);
        if(optionalProductImageEntity.isEmpty()){
            throw new ResourceNotFoundException("ID of Image can not found");
        }
        this.modelMapper.map(productImageUpdateDto, optionalProductImageEntity.get());
        Optional<ProductEntity> optionalProductEntity = this.productRepository.findById(productImageUpdateDto.getProductID());
        if(optionalProductEntity.isEmpty()){
            throw new ResourceNotFoundException("Product ID of Image can not found");
        }
        optionalProductImageEntity.get().setProduct(optionalProductEntity.get());

        this.productImageRepository.save(optionalProductImageEntity.get());

        ProductImageResponseDto productImageResponseDto = this.modelMapper.map(optionalProductImageEntity.get(), ProductImageResponseDto.class);
        productImageResponseDto.setProductID(optionalProductImageEntity.get().getProduct().getId());
        return ResponseEntity.status(HttpStatus.OK).body(productImageResponseDto);
    }
}
