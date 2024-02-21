package com.azizONeill.cart.dto;

import com.azizONeill.cart.model.CartItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CartDTO {
    @Valid

    @NotNull(message = "cartId cannot be null")
    private UUID id;

    @NotNull(message = "userId cannot be null")
    private UUID userId;

    @NotNull(message = "cartItems cannot be null")
    private List<CartItemDTO> cartItems;
}