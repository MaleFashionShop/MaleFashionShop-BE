package com.malefashionshop.service;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.OrderUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.OrderResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderService {
    public List<OrderResponseDto> getAllOrders();

    OrderResponseDto createOrder(OrderUpdateDto orderUpdateDto);
}
