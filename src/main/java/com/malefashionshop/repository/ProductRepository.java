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
}
