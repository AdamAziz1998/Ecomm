package com.azizONeill.cart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateCartItemDTO {

    @NotNull(message = "cartId cannot be null")
    private UUID cartId;

    @NotNull(message = "productId cannot be null")
    private UUID productId;

    @NotNull(message = "quantity cannot be null")
    @Positive(message = "quantity must be positive")
    private int quantity;
}
