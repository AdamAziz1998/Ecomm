package com.azizONeill.product.service;

import com.azizONeill.product.dto.CategoryDTO;
import com.azizONeill.product.dto.CreateCategoryDTO;
import com.azizONeill.product.dto.UpdateCategoryDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO createCategory(CreateCategoryDTO createCategoryDTO);

    CategoryDTO getCategoryById(UUID categoryId);

    List<CategoryDTO> getAllCategories();

    CategoryDTO updateCategory(UUID categoryId, UpdateCategoryDTO updateCategoryDTO);

    void deleteCategory(UUID categoryId);
}
