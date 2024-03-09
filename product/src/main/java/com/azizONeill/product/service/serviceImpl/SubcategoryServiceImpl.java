package com.azizONeill.product.service.serviceImpl;

import com.azizONeill.product.dto.*;
import com.azizONeill.product.dto.convert.DTOConverter;
import com.azizONeill.product.model.Category;
import com.azizONeill.product.model.Subcategory;
import com.azizONeill.product.repository.CategoryRepository;
import com.azizONeill.product.repository.SubcategoryRepository;
import com.azizONeill.product.service.SubcategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubcategoryServiceImpl implements SubcategoryService {

    private final DTOConverter DTOConverter;
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SubcategoryServiceImpl(
            DTOConverter DTOConverter,
            SubcategoryRepository subcategoryRepository,
            CategoryRepository categoryRepository
    ) {
        this.DTOConverter = DTOConverter;
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SubcategoryDTO createSubcategory(CreateSubcategoryDTO createSubcategoryDTO) {
        Subcategory subcategory = new Subcategory();

        subcategory.setName(createSubcategoryDTO.getName());

        Category category = categoryRepository
                .findById(createSubcategoryDTO.getCategory())
                .orElse(null);

        if (category == null) {
            return null;
        }

        category.getSubcategories().add(subcategory);
        categoryRepository.save(category);
        Subcategory newSubcategory = subcategoryRepository.save(subcategory);

        return DTOConverter.convertSubcategoryToSubcategoryDTO(newSubcategory);
    }

    @Override
    public SubcategoryDTO getSubcategoryById(UUID subcategoryId) {

        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElse(null);

        if (subcategory == null) {
            return null;
        }

        return DTOConverter.convertSubcategoryToSubcategoryDTO(subcategory);
    }

    @Override
    public List<SubcategoryDTO> getAllSubcategories() {

        List<Subcategory> subcategories = subcategoryRepository.findAll();

        return subcategories.stream().map(DTOConverter::convertSubcategoryToSubcategoryDTO).toList();
    }

    @Override
    public SubcategoryDTO updateSubcategory(UUID subcategoryId, UpdateSubcategoryDTO updateSubcategoryDTO) {

        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElse(null);

        if (subcategory == null) {
            return null;
        }

        subcategory.setName(updateSubcategoryDTO.getName());
        subcategoryRepository.save(subcategory);

        return DTOConverter.convertSubcategoryToSubcategoryDTO(subcategory);
    }

    @Override
    public void deleteSubcategory(UUID subcategoryId) {

        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElse(null);

        if (subcategory == null) {
            //throw error here
        }

        subcategoryRepository.delete(subcategory);
    }
}
