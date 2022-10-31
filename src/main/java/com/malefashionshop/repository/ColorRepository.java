package com.malefashionshop.repository;

import com.malefashionshop.entities.ColorEntity;
import com.malefashionshop.entities.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<ColorEntity,Long> {

}
