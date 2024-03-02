package com.azizONeill.cart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class DeleteCartItemDTO {
    @Valid

    @NotNull(message = "cartItemId cannot be null")
    private UUID cartItemId;

    @NotNull(message = "cartId cannot be null")
    private UUID cartId;
}
