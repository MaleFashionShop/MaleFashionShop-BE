package com.malefashionshop.entities;

import com.malefashionshop.entities.EntityKey.OrderItemKey;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="order_item")
public class OrderItemEntity {

    @EmbeddedId
    OrderItemKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    OrderEntity order;

    @ManyToOne
    @MapsId("productDetailID")
    @JoinColumn(name = "product_detail_id")
    ProductDetailEntity productDetail;

    int quantity;
}
