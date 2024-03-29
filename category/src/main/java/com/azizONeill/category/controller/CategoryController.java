package com.azizONeill.category.controller;

import com.azizONeill.category.dto.CategoryDTO;
import com.azizONeill.category.dto.CreateCategoryDTO;
import com.azizONeill.category.dto.ProductDTO;
import com.azizONeill.category.dto.UpdateCategoryDTO;
import com.azizONeill.category.model.enums.SuperCategory;
import com.azizONeill.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CreateCategoryDTO createCategoryDTO) {
        CategoryDTO categoryDTO = categoryService.createCategory(createCategoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable UUID categoryId) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);

    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOs);
    }

    @PutMapping("category/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable UUID categoryId, @RequestBody UpdateCategoryDTO updateCategoryDTO) {
        CategoryDTO categoryDTO = categoryService.updateCategory(categoryId, updateCategoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @DeleteMapping("category/{categoryId}")
    public void deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("category/product/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable UUID categoryId) {
        List<ProductDTO> products = categoryService.getProductsByCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("category/superCategory/{superCategory}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesBySuperCategory(@PathVariable SuperCategory superCategory) {
        List<CategoryDTO> categoryDTOs = categoryService.getCategoriesBySuperCategory(superCategory);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOs);
    }
}
