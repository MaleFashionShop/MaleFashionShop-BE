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
@Table(name="cart")
public class CartEntity extends BaseEntity{
    @Column(name="quantity")
    private int quantity;

    @ManyToOne()
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;

    @OneToOne()
    @JoinColumn(name = "product_detail_id")
    private ProductDetailEntity productDetail;
}
