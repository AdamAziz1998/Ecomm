package com.azizONeill.cart.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "userId")
    private UUID userId;

    @NotNull
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @Column(name = "cartItems")
    private List<CartItem> cartItems;
}
