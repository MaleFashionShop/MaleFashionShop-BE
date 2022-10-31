package com.malefashionshop.repository;

import com.malefashionshop.entities.ProductDetailEntity;
import com.malefashionshop.entities.ProductEntity;
import com.malefashionshop.entities.enums.DeleteEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long> {

    @Query("FROM ProductDetailEntity WHERE deleteEnum = ?1 ")
    List<ProductDetailEntity> findAllByDeleteEnum(DeleteEnum state);
}
