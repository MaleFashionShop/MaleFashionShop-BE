package com.malefashionshop.repository;

import com.malefashionshop.entities.EntityKey.OrderItemKey;
import com.malefashionshop.entities.EntityKey.RatingKey;
import com.malefashionshop.entities.OrderItemEntity;
import com.malefashionshop.entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, RatingKey> {

    @Query("FROM RatingEntity WHERE id.productID = ?1 ")
    List<RatingEntity> findAllByProductId(Long productID);

//    List<OrderItemEntity> findByOrderId(Long id);
}
