package com.malefashionshop.service.impl;

import com.malefashionshop.dto.request.CategoryUpdateDto;
import com.malefashionshop.dto.request.OrderItemUpdateDto;
import com.malefashionshop.dto.response.CategoryResponseDto;
import com.malefashionshop.dto.response.OrderItemResponseDto;
import com.malefashionshop.dto.response.ResponseDto;
import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.OrderItemEntity;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.mappers.OrderItemEntityAndOrderItemResponseDtoMapper;
import com.malefashionshop.repository.CategoryRepository;
import com.malefashionshop.repository.OrderItemRepository;
import com.malefashionshop.service.ICategoryService;
import com.malefashionshop.service.IOrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService implements IOrderItemService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderItemEntityAndOrderItemResponseDtoMapper orderItemAndResponseDtoMapper;

    @Override
    public List<OrderItemResponseDto> getAllOrderItems() {
        List<OrderItemEntity> listOrderItemEntity = this.orderItemRepository.findAll();
        List<OrderItemResponseDto> listOrderItemResponseDto = new ArrayList<>();
        listOrderItemEntity.forEach(orderItemEntity -> {
            OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
            this.orderItemAndResponseDtoMapper.map(orderItemEntity, orderItemResponseDto);
            listOrderItemResponseDto.add(orderItemResponseDto);
        });

        return listOrderItemResponseDto;
    }

    @Override
    public OrderItemResponseDto createOrderItem(OrderItemUpdateDto orderItemUpdateDto) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();

        this.orderItemAndResponseDtoMapper.map(orderItemUpdateDto,orderItemEntity);

        this.orderItemRepository.save(orderItemEntity);

        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
        this.orderItemAndResponseDtoMapper.map(orderItemEntity,orderItemResponseDto);

        return orderItemResponseDto;
    }

    @Override
    public List<OrderItemResponseDto> getAllOrdersItemByOrderID(Long id) {
        List<OrderItemEntity> listOrderItemEntity = this.orderItemRepository.findByOrderId(id);
        List<OrderItemResponseDto> listOrderItemResponseDto = new ArrayList<>();

        listOrderItemEntity.forEach(orderItemEntity->{
            OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
            this.orderItemAndResponseDtoMapper.map(orderItemEntity,orderItemResponseDto);
            listOrderItemResponseDto.add(orderItemResponseDto);
        });
        return listOrderItemResponseDto;
    }

//    @Override
//    public OrderItemResponseDto getOrderItemByID(Long id) {
//        Optional<OrderItemEntity> optionalOrderItemEntity = this.orderItemRepository.findById(id);
//        if(optionalOrderItemEntity.isEmpty()){
//            throw new ResourceNotFoundException("Order Item with ID: "+id+" can be not found");
//        }
//        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
//        this.orderItemAndResponseDtoMapper.map(optionalOrderItemEntity.get(), orderItemResponseDto);
//        return orderItemResponseDto;
//    }


}
