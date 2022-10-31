package com.malefashionshop.entities;

import com.malefashionshop.entities.EntityKey.OrderItemKey;
import com.malefashionshop.entities.EntityKey.RatingKey;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="rating")
public class RatingEntity {
    @EmbeddedId
    RatingKey id;

    @Column(name="comment")
    private String comment;

    @Column(name="score")
    private int score;

    @ManyToOne
    @MapsId("customerID")
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @ManyToOne
    @MapsId("productID")
    @JoinColumn(name = "product_id")
    ProductEntity product;

}
