package com.malefashionshop.repository;

import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<SizeEntity,Long> {

    SizeEntity findByName(String name);
}
