package com.azizONeill.product.dto;

import com.azizONeill.product.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductVariantDTO {
    @NotNull(message = "status cannot be null")
    private Status status;

    @NotNull(message = "price cannot be null")
    @Positive(message = "price must be positive")
    private double price;

    @NotNull(message = "stockQuantity cannot be null")
    @Positive(message = "stockQuantity must be positive")
    private int stockQuantity;

    @NotNull(message = "imageUrl cannot be null")
    @NotBlank(message = "imageUrl cannot be empty")
    private String imageUrl;

    @NotBlank(message = "color cannot be empty")
    private String color;

    @NotBlank(message = "size cannot be empty")
    private String size;

    @NotBlank(message = "flavour cannot be empty")
    private String flavour;

    public boolean isColorPresent() {
        return color != null;
    }

    public boolean isSizePresent() {
        return size != null;
    }

    public boolean isFlavourPresent() {
        return flavour != null;
    }
}
