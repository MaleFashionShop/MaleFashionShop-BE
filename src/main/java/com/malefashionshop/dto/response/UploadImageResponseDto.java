package com.malefashionshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class UploadImageResponseDto {
    private List<ProductImageResponseDto> listProductImageResponse;
}
