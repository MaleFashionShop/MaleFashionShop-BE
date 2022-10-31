package com.malefashionshop.repository;

import com.malefashionshop.entities.CategoryEntity;
import com.malefashionshop.entities.ProductEntity;
import com.malefashionshop.entities.TagEntity;
import com.malefashionshop.entities.enums.DeleteEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity,Long> {

    TagEntity findByCode(String code);

}
