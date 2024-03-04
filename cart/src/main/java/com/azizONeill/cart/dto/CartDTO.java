package com.azizONeill.cart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
public class CartDTO implements Serializable {
    @Valid

    @NotNull(message = "cartId is required")
    private UUID id;

    @NotNull(message = "userId cannot be null")
    private UUID userId;

    @NotNull(message = "cartItems cannot be null")
    private Set<CartItemDTO> cartItems;
}