package com.azizONeill.cart.dto;

import com.azizONeill.cart.dto.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductCartDTO {

    @NotNull(message = "productId cannot be null")
    private UUID productId;

    @NotNull(message = "productVariantId cannot be null")
    private UUID productVariantId;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    @Size(min = 2, message = "name must be at least 2 characters")
    private String name;

    @NotNull(message = "imageUrl cannot be null")
    @NotBlank(message = "imageUrl cannot be empty")
    private String imageUrl;

    @NotNull(message = "status cannot be null")
    private Status status;

    @NotNull(message = "price cannot be null")
    @Positive(message = "price must be positive")
    private double price;

    @NotBlank(message = "color cannot be empty")
    private String color;

    @NotBlank(message = "size cannot be empty")
    private String size;

    @NotBlank(message = "flavour cannot be empty")
    private String flavour;

    @NotNull(message = "quantity cannot be null")
    @Positive(message = "quantity must be positive")
    private int quantity;
}
