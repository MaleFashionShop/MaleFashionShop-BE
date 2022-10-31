package com.malefashionshop.repository;

import com.malefashionshop.entities.CartEntity;
import com.malefashionshop.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,Long> {

    @Query("FROM CartEntity WHERE customer.id = ?1 ")
    List<CartEntity> findAllByCustomerID(Long id);
}
