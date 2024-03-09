package com.azizONeill.product.controller;

import com.azizONeill.product.dto.CreateSubcategoryDTO;
import com.azizONeill.product.dto.SubcategoryDTO;
import com.azizONeill.product.dto.UpdateSubcategoryDTO;
import com.azizONeill.product.service.SubcategoryService;
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

        if (subcategoryDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(subcategoryDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/subcategory")
    public ResponseEntity<List<SubcategoryDTO>> getAllSubcategories() {

        List<SubcategoryDTO> categoryDTOs = subcategoryService.getAllSubcategories();

        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOs);
    }

    @PutMapping("subcategory/{subcategoryId}")
    public ResponseEntity<SubcategoryDTO> updateSubcategory(@PathVariable UUID subcategoryId, @RequestBody UpdateSubcategoryDTO updateSubcategoryDTO) {

        SubcategoryDTO subcategoryDTO = subcategoryService.updateSubcategory(subcategoryId, updateSubcategoryDTO);

        if (subcategoryDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(subcategoryDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("subcategory/{subcategoryId}")
    public void deleteSubcategory(@PathVariable UUID subcategoryId) {
        subcategoryService.deleteSubcategory(subcategoryId);
    }
}
