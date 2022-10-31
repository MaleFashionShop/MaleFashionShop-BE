package com.malefashionshop.service;

import com.malefashionshop.dto.request.CartUpdateDto;
import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.response.CartResponseDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICartService {
    public List<CartResponseDto> getAllCarts();

    CartResponseDto createCart(CartUpdateDto dto);

    List<CartResponseDto> getAllCartsByCustomerID(Long id);

    ResponseEntity<ResponseDto> deleteCart(Long id);

    CartResponseDto getCartByID(Long id);
}
