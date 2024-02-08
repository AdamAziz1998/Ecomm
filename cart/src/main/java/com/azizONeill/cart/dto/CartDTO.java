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

    @NotNull(message = "cartId is mandatory")
    private UUID id;

    @NotNull(message = "userId is mandatory")
    @NotBlank(message = "userId is mandatory")
    private UUID userId;

    @NotNull(message = "cartItems is mandatory")
    @NotBlank(message = "cartItems is mandatory")
    private List<CartItem> cartItems;
}