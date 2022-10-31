package com.malefashionshop.controller;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.OrderItemUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.OrderItemResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.service.impl.CategoryService;
import com.malefashionshop.service.impl.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    List<OrderItemResponseDto> getAllOrderItems(){
        return this.orderItemService.getAllOrderItems();
    }

    @GetMapping("/order/{id}")
    List<OrderItemResponseDto> getOrderItemByID(@PathVariable("id") Long id){
        return this.orderItemService.getAllOrdersItemByOrderID(id);
    }

    @PostMapping
    OrderItemResponseDto createOrderItem(@RequestBody OrderItemUpdateDto orderItemUpdateDto){
        return this.orderItemService.createOrderItem(orderItemUpdateDto);
    }

}
