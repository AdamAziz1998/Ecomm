package com.azizONeill.userservice.model;

import com.azizONeill.userservice.model.enums.Active;
import com.azizONeill.userservice.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Active active;

    @Embedded
    private UserDetails userDetails;
}