package com.azizONeill.cart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateCartDTO {

    @NotNull(message = "userId cannot be null")
    private UUID userId;

    @NotNull(message = "cartItems cannot be null")
    private List<CartItemDTO> cartItems;
}
