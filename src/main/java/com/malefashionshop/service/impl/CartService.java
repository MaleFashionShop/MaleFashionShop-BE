package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.CartUpdateDto;
import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.response.CartResponseDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.entities.CartEntity;
import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.mappers.CartEntityAndCartResponseDtoMapper;
import com.malefashionshop.repository.CartRepository;
import com.malefashionshop.repository.CategoryRepository;
import com.malefashionshop.service.ICartService;
import com.malefashionshop.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartEntityAndCartResponseDtoMapper cartEntityAndResponseDtoMapper;

    @Override
    public List<CartResponseDto> getAllCarts() {
        List<CartEntity> listCartEntity = this.cartRepository.findAll();

        List<CartResponseDto> listCartResponseDto = new ArrayList<>();
        listCartEntity.forEach(cartEntity->{
            CartResponseDto cartResponseDto = new CartResponseDto();
            this.cartEntityAndResponseDtoMapper.map(cartEntity, cartResponseDto);
            listCartResponseDto.add(cartResponseDto);
        });

        return listCartResponseDto;
    }

    @Override
    public CartResponseDto createCart(CartUpdateDto dto) {
        CartEntity cartEntity = new CartEntity();

        this.cartEntityAndResponseDtoMapper.map(dto, cartEntity);

        this.cartRepository.save(cartEntity);

        CartResponseDto cartResponseDto = new CartResponseDto();
        this.cartEntityAndResponseDtoMapper.map(cartEntity,cartResponseDto);

        return cartResponseDto;
    }

    @Override
    public List<CartResponseDto> getAllCartsByCustomerID(Long id) {
        List<CartEntity> listCartEntity = this.cartRepository.findAllByCustomerID(id);

        List<CartResponseDto> listCartResponseDto = new ArrayList<>();
        listCartEntity.forEach(cartEntity->{
            CartResponseDto cartResponseDto = new CartResponseDto();
            this.cartEntityAndResponseDtoMapper.map(cartEntity, cartResponseDto);
            listCartResponseDto.add(cartResponseDto);
        });

        return listCartResponseDto;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCart(Long id) {
        Optional<CartEntity> optionalCartEntity =  this.cartRepository.findById(id);
        if(optionalCartEntity.isEmpty()){
            throw new ResourceNotFoundException("Cart with ID: "+id+"can be not found");
        }

        this.cartRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Success Fully!","200"));
    }

    @Override
    public CartResponseDto getCartByID(Long id) {
        Optional<CartEntity> optionalCartEntity =  this.cartRepository.findById(id);
        if(optionalCartEntity.isEmpty()){
            throw new ResourceNotFoundException("Cart with ID: "+id+"can be not found");
        }

        CartResponseDto cartResponseDto = new CartResponseDto();
        this.cartEntityAndResponseDtoMapper.map(optionalCartEntity.get(), cartResponseDto);

        return cartResponseDto;
    }
}
