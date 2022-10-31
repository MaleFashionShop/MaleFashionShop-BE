package com.malefashionshop.entities;

import com.malefashionshop.entities.enums.DeleteEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="product_detail")
public class ProductDetailEntity extends BaseEntity {

    @Column(name="price")
    private float price;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne()
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @Column(name="state")
    @Enumerated(EnumType.STRING)
    private DeleteEnum deleteEnum;

    @ManyToOne()
    @JoinColumn(name = "size_id")
    private SizeEntity size;

    @ManyToOne()
    @JoinColumn(name = "color_id")
    private ColorEntity color;

    @OneToMany(mappedBy = "productDetail")
    private List<OrderItemEntity> listOrderItem;

//    @OneToOne()
//    private CartEntity cartEntity;

}
