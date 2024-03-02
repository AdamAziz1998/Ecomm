package com.azizONeill.cart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class CartItemDTO implements Serializable {
    @Valid

    @NotNull(message = "cartItemId cannot be null")
    private UUID id;

    @NotNull(message = "productId cannot be null")
    private UUID productId;

    @NotNull(message = "quantity cannot be null")
    @Positive(message = "quantity must be positive")
    private int quantity;
}
