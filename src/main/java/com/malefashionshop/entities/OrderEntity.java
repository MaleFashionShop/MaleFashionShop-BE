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
@Table(name="order")
public class OrderEntity extends BaseEntity{

    @Column(name="state")
    @Enumerated(EnumType.STRING)
    private DeleteEnum deleteEnum;

    @ManyToOne()
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItems;

    @PreRemove
    private void preRemove() {
        this.orderItems.forEach(product->{
            product.setOrder(null);
        });
    }

}
