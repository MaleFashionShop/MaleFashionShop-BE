package com.malefashionshop.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="size")
public class SizeEntity extends BaseEntity {
    @Column(name="name", unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "size")
    private List<ProductDetailEntity> listProductDetailEntity;

    @PreRemove
    private void preRemove() {
        this.listProductDetailEntity.forEach(productDetailEntity->{
            productDetailEntity.setSize(null);
        });
    }
}
