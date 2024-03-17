package com.azizONeill.cart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter(onMethod_ = @JsonIgnore)
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CartItem")
public class CartItem {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotNull
    @Column(name = "productId")
    private UUID productId;

    @NotNull
    @Column(name = "productVariantId")
    private UUID productVariantId;

    @NotNull
    @Positive
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;
}
