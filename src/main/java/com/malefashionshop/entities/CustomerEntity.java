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
@Table(name="customer")
public class CustomerEntity extends BaseEntity {
    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Column(name="state")
    @Enumerated(EnumType.STRING)
    private DeleteEnum deleteEnum;

    @OneToMany(mappedBy = "customer")
    List<OrderEntity> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    List<RatingEntity> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    List<CartEntity> carts = new ArrayList<>();


}
