package com.malefashionshop.entities;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product_images")
public class ProductImageEntity extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public ProductImageEntity(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
