package com.azizONeill.cart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class CartItemDTO {
    @Valid

    @NotNull(message = "cartItemId cannot be null")
    @NotEmpty(message = "cartItemId cannot be empty")
    private UUID id;

    @NotNull(message = "productId cannot be null")
    @NotBlank(message = "productId cannot be empty")
    private UUID productId;

    @NotNull(message = "quantity cannot be null")
    @NotBlank(message = "quantity cannot be empty")
    @Positive(message = "quantity must be positive")
    private int quantity;
}
