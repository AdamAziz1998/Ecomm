package com.azizONeill.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class CategoryDTO {

    @NotNull(message = "categoryId cannot be null")
    private UUID id;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    @Size(min = 2, message = "name must be at least 2 characters")
    private String name;

    @NotNull(message = "subcategories cannot be null")
    private Set<SubcategoryDTO> subcategoryDTOs;
}