package com.azizONeill.cart.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateCartItemDTO {

    @NotNull(message = "userId cannot be null")
    private UUID cartItemId;

    @NotNull(message = "quantity cannot be null")
    @Positive(message = "quantity must be positive")
    private int quantity;
}
