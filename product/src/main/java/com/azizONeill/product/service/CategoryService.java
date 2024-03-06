package com.azizONeill.product.service;

import com.azizONeill.product.dto.CategoryDTO;
import com.azizONeill.product.dto.CreateCategoryDTO;

import java.util.Set;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO createCategory(CreateCategoryDTO createCategoryDTO);

    CategoryDTO getCategoryById(UUID categoryId);

    Set<CategoryDTO> getAllCategories();

    CategoryDTO updateCategory(UpdateCategoryDTO updateCategoryDTO);

    void deleteCategory(UUID categoryID);
}
