package com.malefashionshop.repository;

import com.malefashionshop.entities.BrandEntity;
import com.malefashionshop.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity,Long> {

}
