package com.azizONeill.cart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class RemoveCartItemDTO {
    @Valid

    @NotNull(message = "userId cannot be null")
    @NotBlank(message = "userId cannot be empty")
    private UUID userId;

    @NotNull(message = "productId cannot be null")
    @NotBlank(message = "productId cannot be empty")
    private UUID productId;
}
