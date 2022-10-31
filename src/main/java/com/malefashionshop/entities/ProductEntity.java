package com.malefashionshop.entities;

import com.malefashionshop.entities.enums.DeleteEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class ProductEntity extends BaseEntity{

    @Column(name="name")
    private String name;

    @Column(name ="feature_image")
    private String featureImage;

    @Column(name="description")
    private String description;

    @Column(name="state")
    @Enumerated(EnumType.STRING)
    private DeleteEnum deleteEnum;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToMany(mappedBy = "products")
    private List<TagEntity> tags;

    @OneToMany(mappedBy = "product")
    private List<RatingEntity> ratings;

    @OneToMany(mappedBy = "product")
    private List<ProductImageEntity> images;


}
