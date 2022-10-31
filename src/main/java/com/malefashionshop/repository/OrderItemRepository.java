package com.malefashionshop.repository;

import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.EntityKey.OrderItemKey;
import com.malefashionshop.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemKey> {

    List<OrderItemEntity> findByOrderId(Long id);
}
