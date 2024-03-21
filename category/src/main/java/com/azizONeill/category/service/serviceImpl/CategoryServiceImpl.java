package com.azizONeill.category.service.serviceImpl;


import com.azizONeill.category.client.ProductClient;
import com.azizONeill.category.config.exceptions.notFound.ResourceNotFoundException;
import com.azizONeill.category.dto.CategoryDTO;
import com.azizONeill.category.dto.CreateCategoryDTO;
import com.azizONeill.category.dto.ProductDTO;
import com.azizONeill.category.dto.UpdateCategoryDTO;
import com.azizONeill.category.dto.convert.DTOConverter;
import com.azizONeill.category.model.Category;
import com.azizONeill.category.model.enums.SuperCategory;
import com.azizONeill.category.repository.CategoryRepository;
import com.azizONeill.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final DTOConverter DTOConverter;
    private final CategoryRepository categoryRepository;
    private final ProductClient productClient;

    @Override
    public CategoryDTO createCategory(CreateCategoryDTO createCategoryDTO) {

        Category category = new Category();
        category.setName(createCategoryDTO.getName());
        category.setSubcategories(new ArrayList<>());
        Category newCategory = categoryRepository.save(category);
        return DTOConverter.convertCategoryToCategoryDTO(newCategory);
    }

    @Override
    public CategoryDTO getCategoryById(UUID categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found with id: " + categoryId));
        return DTOConverter.convertCategoryToCategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(DTOConverter::convertCategoryToCategoryDTO).toList();
    }

    @Override
    public CategoryDTO updateCategory(UUID categoryId, UpdateCategoryDTO updateCategoryDTO) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found with id: " + categoryId));
        category.setName(updateCategoryDTO.getName());
        categoryRepository.save(category);
        return DTOConverter.convertCategoryToCategoryDTO(category);
    }

    @Override
    @CacheEvict(value = "subcategory", allEntries = true)
    public void deleteCategory(UUID categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found with id: " + categoryId));
        categoryRepository.delete(category);

    }

    @Override
    public List<ProductDTO> getProductsByCategory(UUID categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found with id: " + categoryId));
        return category.getSubcategories().stream().flatMap(subcategory -> subcategory.getProducts().stream().map(productClient::findProductById)).toList();
    }

    @Override
    public List<CategoryDTO> getCategoriesBySuperCategory(SuperCategory superCategory) {
        List<Category> categories = categoryRepository.findBySuperCategory(superCategory);
        return categories.stream().map(DTOConverter::convertCategoryToCategoryDTO).toList();
    }

}
