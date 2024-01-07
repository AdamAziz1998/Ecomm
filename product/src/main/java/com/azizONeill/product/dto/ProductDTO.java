package com.azizONeill.product.dto;


import com.azizONeill.product.model.enums.Category;
import com.azizONeill.product.model.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDTO {

    private UUID id;

    @Valid

    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name is mandatory")
    private String name;

    @NotNull(message = "status is mandatory")
    @NotBlank(message = "status is mandatory")
    private Status status;

    @NotNull(message = "price is mandatory")
    @NotBlank(message = "price is mandatory")
    private BigDecimal price;

    @NotNull(message = "stockQuantity is mandatory")
    @NotBlank(message = "stockQuantity is mandatory")
    private int stockQuantity;

    @NotNull(message = "description is mandatory")
    @NotBlank(message = "description is mandatory")
    @Size(min = 5, message = "description must be at least 5 characters")
    private String description;

    @NotNull(message = "imageUrl is mandatory")
    @NotBlank(message = "imageUrl is mandatory")
    private String imageUrl;

    @NotNull(message = "category is mandatory")
    @NotBlank(message = "category is mandatory")
    private Category category;
}
