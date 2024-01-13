package com.azizONeill.cart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateCartItemDTO {
    @Valid

    @NotNull(message = "productId is mandatory")
    @NotBlank(message = "productId is mandatory")
    private UUID productId;

    @NotNull(message = "quantity is mandatory")
    @NotBlank(message = "quantity is mandatory")
    @Positive(message = "quantity must be positive")
    private int quantity;
}
