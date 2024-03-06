package com.azizONeill.product.service.serviceImpl;

import com.azizONeill.product.dto.CategoryDTO;
import com.azizONeill.product.dto.CreateCategoryDTO;
import com.azizONeill.product.dto.UpdateCategoryDTO;
import com.azizONeill.product.dto.convert.DTOConverter;
import com.azizONeill.product.model.Category;
import com.azizONeill.product.repository.CategoryRepository;
import com.azizONeill.product.repository.SubcategoryRepository;
import com.azizONeill.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final DTOConverter DTOConverter;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(
            DTOConverter DTOConverter,
            CategoryRepository categoryRepository
    ) {
        this.DTOConverter = DTOConverter;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO createCategory(CreateCategoryDTO createCategoryDTO) {

        Category category = new Category();

        category.setName(createCategoryDTO.getName());
        category.setSubCategories(new HashSet<>());

        return DTOConverter.convertCategoryToCategoryDTO(category);
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
    public Set<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(DTOConverter::convertCategoryToCategoryDTO).collect(Collectors.toSet());
    }

    @Override
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
    public void deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            //throw error here
        }

        categoryRepository.delete(category);

    }



}
