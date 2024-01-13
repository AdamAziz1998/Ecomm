package com.azizONeill.cart.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CartItem")
public class CartItem {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotNull
    @Column(name = "productId")
    private UUID productId;

    @NotNull
    @Positive
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
