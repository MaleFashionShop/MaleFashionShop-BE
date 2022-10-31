package com.malefashionshop.repository;

import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.CustomerEntity;
import com.malefashionshop.entities.enums.DeleteEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    @Query("FROM CustomerEntity WHERE deleteEnum = ?1 ")
    List<CustomerEntity> findAllByDeleteEnum(DeleteEnum active);
}
