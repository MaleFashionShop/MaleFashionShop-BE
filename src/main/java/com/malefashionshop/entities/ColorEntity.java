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
@Table(name="color")
public class ColorEntity extends BaseEntity {
    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "color")
    private List<ProductDetailEntity> listProductDetailEntity;

    @PreRemove
    private void preRemove() {
        this.listProductDetailEntity.forEach(productDetailEntity->{
            productDetailEntity.setColor(null);
        });
    }
}
