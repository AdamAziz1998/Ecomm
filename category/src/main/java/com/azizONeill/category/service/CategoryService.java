package com.azizONeill.category.service;

import com.azizONeill.category.dto.CategoryDTO;
import com.azizONeill.category.dto.CreateCategoryDTO;
import com.azizONeill.category.dto.ProductDTO;
import com.azizONeill.category.dto.UpdateCategoryDTO;
import com.azizONeill.category.model.enums.SuperCategory;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO createCategory(CreateCategoryDTO createCategoryDTO);

    CategoryDTO getCategoryById(UUID categoryId);

    List<CategoryDTO> getAllCategories();

    CategoryDTO updateCategory(UUID categoryId, UpdateCategoryDTO updateCategoryDTO);

    void deleteCategory(UUID categoryId);

    List<ProductDTO> getProductsByCategory(UUID categoryId);

    List<CategoryDTO> getCategoriesBySuperCategory(SuperCategory superCategory);
}
