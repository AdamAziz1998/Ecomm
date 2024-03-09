package com.azizONeill.product.dto;

import com.azizONeill.product.model.enums.Status;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class UpdateProductDTO {

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    @Size(min = 2, message = "name must be at least 2 characters")
    private String name;

    @NotNull(message = "status cannot be null")
    private Status status;

    @NotNull(message = "price cannot be null")
    @Positive(message = "price must be positive")
    private BigDecimal price;

    @NotNull(message = "stockQuantity cannot be null")
    @PositiveOrZero(message = "stockQuantity must be at least 0")
    private int stockQuantity;

    @NotNull(message = "description cannot be null")
    @NotBlank(message = "description cannot be empty")
    @Size(min = 5, message = "description must be at least 5 characters")
    private String description;

    @NotNull(message = "imageUrl cannot be null")
    @NotBlank(message = "imageUrl cannot be empty")
    private String imageUrl;

}
