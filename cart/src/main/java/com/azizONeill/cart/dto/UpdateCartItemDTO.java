package com.azizONeill.cart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateCartItemDTO {
    @Valid

    @NotNull(message = "userId cannot be null")
    @NotBlank(message = "userId cannot be empty")
    private UUID cartItemId;

    @NotNull(message = "quantity cannot be null")
    @NotBlank(message = "quantity cannot be empty")
    @Positive(message = "quantity must be positive")
    private int quantity;
}
