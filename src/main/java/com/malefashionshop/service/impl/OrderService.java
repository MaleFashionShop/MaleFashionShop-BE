package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.OrderUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.OrderResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.OrderEntity;
import com.malefashionshop.entities.OrderItemEntity;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.mappers.OrderEntityAndOrderResponseDtoMapper;
import com.malefashionshop.mappers.OrderItemEntityAndOrderItemResponseDtoMapper;
import com.malefashionshop.repository.CategoryRepository;
import com.malefashionshop.repository.OrderItemRepository;
import com.malefashionshop.repository.OrderRepository;
import com.malefashionshop.service.ICategoryService;
import com.malefashionshop.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderEntityAndOrderResponseDtoMapper orderEntityAndOrderResponseDtoMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<OrderEntity> listOrderEntity = this.orderRepository.findAll();
        List<OrderResponseDto> listOrderResponseDto = new ArrayList<>();
        listOrderEntity.forEach(orderEntity -> {
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            this.orderEntityAndOrderResponseDtoMapper.map(orderEntity, orderResponseDto);
            orderResponseDto.setTotalPrice(calculateTotalPrice(orderEntity));
            listOrderResponseDto.add(orderResponseDto);
        });

//        this.orderEntityAndOrderResponseDtoMapper.map();
        return listOrderResponseDto;
    }

    @Override
    public OrderResponseDto createOrder(OrderUpdateDto orderUpdateDto) {
        OrderEntity orderEntity = new OrderEntity();
        this.orderEntityAndOrderResponseDtoMapper.map(orderUpdateDto, orderEntity);

        this.orderRepository.save(orderEntity);

//        orderEntity.getOrderItems().forEach(orderItem->{
//            orderItem.setOrder(orderEntity);
//            this.orderItemRepository.save(orderItem);
//        });

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        this.orderEntityAndOrderResponseDtoMapper.map(orderEntity, orderResponseDto);

        return orderResponseDto;
    }

    public float calculateTotalPrice(OrderEntity orderEntity){
        float totalPrice = 0;
        for(OrderItemEntity orderItem: orderEntity.getOrderItems()){
            float productPrice = orderItem.getProductDetail().getPrice();
            int orderItemQuantity = orderItem.getQuantity();
            totalPrice += productPrice*orderItemQuantity;
        }
        return totalPrice;
    }
}
