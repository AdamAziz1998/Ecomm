package com.azizONeill.product.controller;

import com.azizONeill.product.dto.CategoryDTO;
import com.azizONeill.product.dto.CreateCategoryDTO;
import com.azizONeill.product.dto.UpdateCategoryDTO;
import com.azizONeill.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
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

        if (categoryDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();

        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOs);
    }

    @PutMapping("category/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable UUID categoryId, @RequestBody UpdateCategoryDTO updateCategoryDTO) {

        CategoryDTO categoryDTO = categoryService.updateCategory(categoryId, updateCategoryDTO);

        if (categoryDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("category/{categoryId}")
    public void deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
