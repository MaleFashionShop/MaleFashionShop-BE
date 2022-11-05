package com.malefashionshop.repository;

import com.malefashionshop.entities.ProductEntity;
import com.malefashionshop.entities.enums.DeleteEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.plaf.nimbus.State;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("FROM ProductEntity WHERE deleteEnum = ?1 ")
    List<ProductEntity> findAllByDeleteEnum(DeleteEnum state);

    @Query("FROM ProductEntity WHERE category.id = ?1 ")
    List<ProductEntity> findAllByCategory(Long id);

    @Query("FROM ProductEntity WHERE brand.id = ?1 ")
    List<ProductEntity> findAllByBrand(Long brandID);
}
