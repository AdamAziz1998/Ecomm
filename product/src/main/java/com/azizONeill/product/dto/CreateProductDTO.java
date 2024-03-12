package com.azizONeill.product.dto;

import com.azizONeill.product.model.enums.Status;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreateProductDTO {

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    @Size(min = 2, message = "name must be at least 2 characters")
    private String name;


    @NotNull(message = "displayPrice cannot be null")
    @Positive(message = "displayPrice must be positive")
    private double displayPrice;

    @NotNull(message = "description cannot be null")
    @NotBlank(message = "description cannot be empty")
    @Size(min = 5, message = "description must be at least 5 characters")
    private String description;

    @NotNull(message = "imageUrl cannot be null")
    @NotBlank(message = "imageUrl cannot be empty")
    private String imageUrl;
}
