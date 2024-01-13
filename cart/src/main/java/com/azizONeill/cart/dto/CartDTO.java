package com.azizONeill.cart.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CartDTO {

    private UUID id;

    @Valid

    @NotNull(message = "userID must be mandatory")
    @NotBlank(message = "userID must be mandatory")
    private UUID userID;

    @NotNull(message = "cartItems must be mandatory")
    private List<CreateCartItemDTO> cartItems;
}
