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
@Table(name="brand")
public class BrandEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<ProductEntity> products = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        this.products.forEach(product->{
            product.setBrand(null);
        });
    }

}
