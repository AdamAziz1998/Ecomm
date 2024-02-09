package com.azizONeill.product.dto;


import com.azizONeill.product.model.enums.Category;
import com.azizONeill.product.model.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDTO {

    private UUID id;

    @Valid

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    @Size(min = 2, message = "name must be at least 2 characters")
    private String name;

    @NotNull(message = "status cannot be null")
    @NotBlank(message = "status cannot be empty")
    private Status status;

    @NotNull(message = "price cannot be null")
    @NotBlank(message = "price cannot be empty")
    @Positive(message = "price must be positive")
    private BigDecimal price;

    @NotNull(message = "stockQuantity cannot be null")
    @NotBlank(message = "stockQuantity cannot be empty")
    @PositiveOrZero(message = "stockQuantity must be at least 0")
    private int stockQuantity;

    @NotNull(message = "description cannot be null")
    @NotBlank(message = "description cannot be empty")
    @Size(min = 5, message = "description must be at least 5 characters")
    private String description;

    @NotNull(message = "imageUrl cannot be null")
    @NotBlank(message = "imageUrl cannot be empty")
    private String imageUrl;

    @NotNull(message = "category cannot be null")
    @NotBlank(message = "category cannot be empty")
    private Category category;
}
