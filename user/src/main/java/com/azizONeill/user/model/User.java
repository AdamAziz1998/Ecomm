package com.azizONeill.user.model;


import com.azizONeill.user.model.enums.Title;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "title")
    private Title title;

    @NotNull
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Column(name = "lastname")
    private String lastName;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;

    @NotNull
    @Column(name = "houseNameNumber")
    private String houseNameNumber;

    @NotNull
    @Column(name = "addressLine1")
    private String addressLine1;

    @NotNull
    @Column(name = "addressLine2")
    private String addressLine2;

    @NotNull
    @Column(name = "townCity")
    private String townCity;

    @NotNull
    @Column(name = "county")
    private String county;

    @NotNull
    @Column(name = "postCode")
    private String postCode;

    @NotNull
    @Column(name = "password")
    private String password;

    //@NotNull
    //@Column(name = "lastLogin")
    //private LocalDateTime lastLogin;

    //@NotNull
    //@Column(name = "accountCreationTime", updatable = false)
    //private LocalDateTime accountCreationTime;
}
