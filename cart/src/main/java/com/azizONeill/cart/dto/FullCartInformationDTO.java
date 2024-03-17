package com.azizONeill.cart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FullCartInformationDTO {

    @NotNull(message = "cartId cannot be null")
    private UUID cartId;

    @Valid
    private List<ProductCartDTO> products;

    @NotNull(message = "totalPrice cannot be null")
    @Positive(message = "total price cannot be less than zero")
    private double totalPrice;


}
