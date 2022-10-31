package com.malefashionshop.controller;

import com.malefashionshop.dto.request.CartUpdateDto;
import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.response.CartResponseDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.service.impl.CartService;
import com.malefashionshop.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    List<CartResponseDto> getAllCarts(){
        return this.cartService.getAllCarts();
    }

    @GetMapping("/customer/{id}")
    List<CartResponseDto> getAllCarts(@PathVariable("id") Long id){
        return this.cartService.getAllCartsByCustomerID(id);
    }

    @GetMapping("/{id}")
    CartResponseDto getCartByID(@PathVariable("id") Long id){
        return this.cartService.getCartByID(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CartResponseDto createCart(@Valid @RequestBody CartUpdateDto dto){
        return this.cartService.createCart(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCart(@PathVariable Long id) {
        return this.cartService.deleteCart(id);
    }
}
