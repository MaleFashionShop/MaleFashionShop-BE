package com.malefashionshop.entities.EntityKey;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderItemKey implements Serializable {
    @Column(name = "order_id")
    Long orderId;

    @Column(name = "product_detail_id")
    Long productDetailID;
}
