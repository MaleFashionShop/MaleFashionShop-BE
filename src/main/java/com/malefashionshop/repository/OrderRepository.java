package com.malefashionshop.repository;

import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

}
