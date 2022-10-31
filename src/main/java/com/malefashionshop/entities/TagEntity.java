package com.malefashionshop.entities;

import com.malefashionshop.entities.enums.DeleteEnum;
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
@Table(name="tag")
public class TagEntity extends BaseEntity {

    @Column(name = "code", unique=true)
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_tag", joinColumns = @JoinColumn(name = "tag_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> products = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        this.products.forEach(product->{
            product.setTags(null);
        });
    }

}
