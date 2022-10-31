package com.malefashionshop.service;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.OrderItemUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.OrderItemResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderItemService {
    List<OrderItemResponseDto> getAllOrderItems();

    OrderItemResponseDto createOrderItem(OrderItemUpdateDto orderItemUpdateDto);

    List<OrderItemResponseDto> getAllOrdersItemByOrderID(Long id);
//
//    OrderItemResponseDto getOrderItemByID(Long id);
}
