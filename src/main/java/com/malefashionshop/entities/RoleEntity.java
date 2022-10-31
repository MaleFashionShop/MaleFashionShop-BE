package com.malefashionshop.entities;

import com.malefashionshop.entities.enums.ERole;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="role", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class RoleEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique=true)
    private ERole name;

    @OneToMany(mappedBy = "role")
    List<UserEntity> users;

    @OneToMany(mappedBy = "role")
    List<CustomerEntity> customers;

    @PreRemove
    private void preRemove() {
        this.customers.forEach(customer->{
            customer.setRole(null);
        });

        this.users.forEach(user->{
            user.setRole(null);
        });
    }

//    public RoleEntity(RoleEntity roleEntity){
//
//    }
}
