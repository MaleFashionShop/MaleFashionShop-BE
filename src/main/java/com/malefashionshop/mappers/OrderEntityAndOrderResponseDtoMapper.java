package com.malefashionshop.mappers;

import com.malefashionshop.dto.request.OrderItemUpdateDto;
import com.malefashionshop.dto.request.OrderUpdateDto;
import com.malefashionshop.dto.response.CustomerResponseDto;
import com.malefashionshop.dto.response.OrderItemResponseDto;
import com.malefashionshop.dto.response.OrderResponseDto;
import com.malefashionshop.dto.response.ProductDetailResponseDto;
import com.malefashionshop.entities.CustomerEntity;
import com.malefashionshop.entities.OrderEntity;
import com.malefashionshop.entities.OrderItemEntity;
import com.malefashionshop.entities.ProductDetailEntity;
import com.malefashionshop.exceptions.DataConstrainConflictException;
import com.malefashionshop.exceptions.ResourceNotFoundException;
import com.malefashionshop.repository.*;
import com.malefashionshop.service.impl.OrderItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderEntityAndOrderResponseDtoMapper {
    @Autowired
    OrderItemEntityAndOrderItemResponseDtoMapper orderItemEntityAndResponseDtoMapper;
    @Autowired
    CustomerEntityAndCustomerResponseDtoMapper customerEntityAndResponseDtoMapper;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    CustomerRepository customerRepository;


    public void map(OrderEntity orderEntity, OrderResponseDto orderResponseDto){
        BeanUtils.copyProperties(orderEntity, orderResponseDto);

        if(orderEntity.getCustomer()!=null) {
            Optional<CustomerEntity> optionalCustomerEntity = this.customerRepository.findById(
                    orderEntity.getCustomer().getId());
            CustomerResponseDto customerResponseDto = new CustomerResponseDto();
            this.customerEntityAndResponseDtoMapper.map(optionalCustomerEntity.get(), customerResponseDto);
            orderResponseDto.setCustomer(customerResponseDto);
        } else {
            orderResponseDto.setCustomer(null);
        }

        List<OrderItemResponseDto> listOrderItemResponseDto = new ArrayList<>();
        if(orderEntity.getOrderItems()!= null) {
            orderEntity.getOrderItems().forEach(orderItemEntity -> {
                OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
                this.orderItemEntityAndResponseDtoMapper.map(orderItemEntity, orderItemResponseDto);
                listOrderItemResponseDto.add(orderItemResponseDto);
            });
            orderResponseDto.setOrderItemResponseDto(listOrderItemResponseDto);
        } else{
            orderResponseDto.setOrderItemResponseDto(null);
        }

    }

    public void map(OrderUpdateDto orderUpdateDto, OrderEntity orderEntity){


        Optional<CustomerEntity> optionalCustomerEntity = this.customerRepository.findById(orderUpdateDto.getCustomerID());
        if(optionalCustomerEntity.isEmpty()){
            throw  new ResourceNotFoundException("Customer with ID: " +orderUpdateDto.getCustomerID()+ " can be not found");
        }
        orderEntity.setCustomer(optionalCustomerEntity.get()); // Tạm thời vì chưa có Customer
//
//        List<OrderItemEntity> listOrderItemEntity = new ArrayList<>();
//        orderUpdateDto.getOrderItemID().forEach(id ->{
//            Optional<OrderItemEntity> optionalOrderItemEntity = this.orderItemRepository.findById(id);
//            if(optionalOrderItemEntity.isEmpty()){
//                throw new ResourceNotFoundException("Order Item with ID: "+id+ "can be not found");
//            }
//            listOrderItemEntity.add(optionalOrderItemEntity.get());
//        });
//        orderEntity.setOrderItems(listOrderItemEntity);


    }
}
