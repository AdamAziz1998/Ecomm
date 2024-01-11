package com.azizONeill.cart.dto;

import com.azizONeill.cart.model.Cart;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class CartItemDTO {
    private UUID id;

    @Valid

    @NotNull(message = "productId is mandatory")
    @NotBlank(message = "productId is mandatory")
    private UUID productId;

    @NotNull(message = "quantity is mandatory")
    @NotBlank(message = "quantity is mandatory")
    @Positive(message = "quantity must be positive")
    private int quantity;

    @NotNull(message = "cart is mandatory")
    @NotBlank(message = "cart is mandatory")
    private Cart cart;
}
