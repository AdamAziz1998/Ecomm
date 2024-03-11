package com.azizONeill.category.service.serviceImpl;


import com.azizONeill.category.client.ProductClient;
import com.azizONeill.category.dto.CategoryDTO;
import com.azizONeill.category.dto.CreateCategoryDTO;
import com.azizONeill.category.dto.ProductDTO;
import com.azizONeill.category.dto.UpdateCategoryDTO;
import com.azizONeill.category.dto.convert.DTOConverter;
import com.azizONeill.category.model.Category;
import com.azizONeill.category.repository.CategoryRepository;
import com.azizONeill.category.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final DTOConverter DTOConverter;
    private final CategoryRepository categoryRepository;
    private final ProductClient productClient;

    @Autowired
    public CategoryServiceImpl(
            DTOConverter DTOConverter,
            CategoryRepository categoryRepository,
            ProductClient productClient
    ) {
        this.DTOConverter = DTOConverter;
        this.categoryRepository = categoryRepository;
        this.productClient = productClient;
    }

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

        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            return null;
        }

        return DTOConverter.convertCategoryToCategoryDTO(category);
    }

    @Override
    @Cacheable(value = "categories")
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(DTOConverter::convertCategoryToCategoryDTO).toList();
    }

    @Override
    @CacheEvict(value = "categories", allEntries = true)
    @CachePut(value = "category", key = "#categoryId")
    public CategoryDTO updateCategory(UUID categoryId, UpdateCategoryDTO updateCategoryDTO) {

        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            return null;
        }

        category.setName(updateCategoryDTO.getName());

        categoryRepository.save(category);

        return DTOConverter.convertCategoryToCategoryDTO(category);
    }

    @Override
    @Caching( evict = {
            @CacheEvict(value = "category", key = "#categoryId"),
            @CacheEvict(value = "categories", allEntries = true)
    })
    public void deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            //throw error here
        }

        categoryRepository.delete(category);

    }

    @Override
    @Cacheable(value = "category")
    public List<ProductDTO> getProductsByCategory(UUID categoryId) {

        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            return null;
        }

        return category.getSubcategories().stream().flatMap(subcategory -> subcategory.getProducts().stream().map(productClient::findProductById)).toList();
    }

}
