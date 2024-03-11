package com.azizONeill.category.service.serviceImpl;

import com.azizONeill.category.client.ProductClient;
import com.azizONeill.category.dto.CreateSubcategoryDTO;
import com.azizONeill.category.dto.ProductDTO;
import com.azizONeill.category.dto.SubcategoryDTO;
import com.azizONeill.category.dto.UpdateSubcategoryDTO;
import com.azizONeill.category.dto.convert.DTOConverter;
import com.azizONeill.category.model.Category;
import com.azizONeill.category.model.Subcategory;
import com.azizONeill.category.repository.CategoryRepository;
import com.azizONeill.category.repository.SubcategoryRepository;
import com.azizONeill.category.service.SubcategoryService;
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
public class SubcategoryServiceImpl implements SubcategoryService {

    private final DTOConverter DTOConverter;
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;
    private final ProductClient productClient;

    @Autowired
    public SubcategoryServiceImpl(
            DTOConverter DTOConverter,
            SubcategoryRepository subcategoryRepository,
            CategoryRepository categoryRepository,
            ProductClient productClient
    ) {
        this.DTOConverter = DTOConverter;
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
        this.productClient = productClient;
    }

    @Override
    public SubcategoryDTO createSubcategory(CreateSubcategoryDTO createSubcategoryDTO) {
        Subcategory subcategory = new Subcategory();

        subcategory.setName(createSubcategoryDTO.getName());
        subcategory.setProducts(new ArrayList<>());

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
    @Cacheable("subcategoriesCache")
    public List<SubcategoryDTO> getAllSubcategories() {

        List<Subcategory> subcategories = subcategoryRepository.findAll();

        return subcategories.stream().map(DTOConverter::convertSubcategoryToSubcategoryDTO).toList();
    }

    @Override
    @CachePut(value = "subcategoryCache", key = "#subcategoryId")
    @CacheEvict(value = "subcategoriesCache", allEntries = true)
    public SubcategoryDTO updateSubcategory(UUID subcategoryId, UpdateSubcategoryDTO updateSubcategoryDTO) {

        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElse(null);

        if (subcategory == null) {
            return null;
        }

        subcategory.setName(updateSubcategoryDTO.getName());
        subcategory.setProducts(updateSubcategoryDTO.getProducts());
        subcategoryRepository.save(subcategory);

        return DTOConverter.convertSubcategoryToSubcategoryDTO(subcategory);
    }

    @Override
    @Caching( evict = {
            @CacheEvict(value = "subcategoryCache", key = "#subcategoryId"),
            @CacheEvict(value = "subcategoriesCache", allEntries = true)
    })
    public void deleteSubcategory(UUID subcategoryId) {

        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElse(null);

        if (subcategory == null) {
            //throw error here
        }

        subcategoryRepository.delete(subcategory);
    }

    @Override
    @Cacheable("subcategoryCache")
    public List<ProductDTO> getProductsBySubcategory(UUID categoryId) {

        Subcategory subcategory = subcategoryRepository.findById(categoryId).orElse(null);

        if (subcategory == null) {
            return null;
        }

        return subcategory.getProducts().stream().map(productClient::findProductById).toList();
    }
}
