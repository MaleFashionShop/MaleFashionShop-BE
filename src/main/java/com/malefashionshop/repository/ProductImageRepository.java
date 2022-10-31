package com.malefashionshop.repository;

import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity,Long> {
    List<ProductImageEntity> findAllByProductId(Long productId);

    List<ProductImageEntity> findByName(String fileName);
}
