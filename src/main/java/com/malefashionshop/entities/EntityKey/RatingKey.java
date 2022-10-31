package com.malefashionshop.entities.EntityKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class RatingKey implements Serializable {

    @Column(name = "customer_id")
    Long customerID;

    @Column(name = "product_id")
    Long productID;
}
