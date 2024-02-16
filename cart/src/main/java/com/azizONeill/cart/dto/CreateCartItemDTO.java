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

    @NotNull(message = "cartId cannot be null")
    @NotBlank(message = "cartId cannot be empty")
    private UUID cartId;


    @NotNull(message = "productId cannot be null")
    @NotBlank(message = "productId cannot be blank")
    private UUID productId;

    @NotNull(message = "quantity cannot be null")
    @NotBlank(message = "quantity cannot be empty")
    @Positive(message = "quantity must be positive")
    private int quantity;
}
