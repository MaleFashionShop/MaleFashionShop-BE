package com.malefashionshop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="admin")
public class UserEntity extends BaseEntity {


    @Column(name = "username")
    @NotBlank
    @Size(max = 20)
    private String username;

    @Column(name = "password")
    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public UserEntity (String username, String password){
        this.username = username;
        this.password = password;
    }
}