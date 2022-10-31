package com.malefashionshop.repository;

import com.malefashionshop.entities.RoleEntity;
import com.malefashionshop.entities.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findByName(ERole name);
}
