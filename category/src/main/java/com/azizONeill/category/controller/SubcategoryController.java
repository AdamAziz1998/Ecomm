package com.azizONeill.category.controller;

import com.azizONeill.category.dto.CreateSubcategoryDTO;
import com.azizONeill.category.dto.ProductDTO;
import com.azizONeill.category.dto.SubcategoryDTO;
import com.azizONeill.category.dto.UpdateSubcategoryDTO;
import com.azizONeill.category.service.SubcategoryService;
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
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @PostMapping("/subcategory")
    public ResponseEntity<SubcategoryDTO> createSubcategory(@Valid @RequestBody CreateSubcategoryDTO createSubcategoryDTO) {
        SubcategoryDTO subcategoryDTO = subcategoryService.createSubcategory(createSubcategoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(subcategoryDTO);
    }

    @GetMapping("/subcategory/{subcategoryId}")
    public ResponseEntity<SubcategoryDTO> getSubcategoryById(@PathVariable UUID subcategoryId) {
        SubcategoryDTO subcategoryDTO = subcategoryService.getSubcategoryById(subcategoryId);
        return ResponseEntity.status(HttpStatus.OK).body(subcategoryDTO);
    }

    @GetMapping("/subcategory")
    public ResponseEntity<List<SubcategoryDTO>> getAllSubcategories() {

        List<SubcategoryDTO> categoryDTOs = subcategoryService.getAllSubcategories();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOs);
    }

    @PutMapping("subcategory/{subcategoryId}")
    public ResponseEntity<SubcategoryDTO> updateSubcategory(@PathVariable UUID subcategoryId, @RequestBody UpdateSubcategoryDTO updateSubcategoryDTO) {
        SubcategoryDTO subcategoryDTO = subcategoryService.updateSubcategory(subcategoryId, updateSubcategoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(subcategoryDTO);
    }

    @DeleteMapping("subcategory/{subcategoryId}")
    public void deleteSubcategory(@PathVariable UUID subcategoryId) {
        subcategoryService.deleteSubcategory(subcategoryId);
    }

    @GetMapping("subcategory/product/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable UUID subcategoryId) {
        List<ProductDTO> products = subcategoryService.getProductsBySubcategory(subcategoryId);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
