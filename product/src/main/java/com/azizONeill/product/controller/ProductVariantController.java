package com.azizONeill.product.controller;

import com.azizONeill.product.dto.*;
import com.azizONeill.product.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/api/v1")
@Slf4j
public class ProductVariantController {

    @Autowired
    private ProductVariantService productVariantService;

    @PostMapping("/productVariant")
    public ResponseEntity<ProductVariantDTO> createProductVariant(@Valid @RequestBody CreateProductVariantDTO createProductVariantDTO) {

        ProductVariantDTO productVariantDTO = productVariantService.createProductVariant(createProductVariantDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productVariantDTO);
    }

    @PutMapping("/productVariant/{id}")
    public ResponseEntity<ProductVariantDTO> updateProductVariant(@PathVariable UUID id, @Valid @RequestBody UpdateProductVariantDTO updateProductVariantDTO) {

        ProductVariantDTO productVariantDTO = productVariantService.updateProductVariant(id, updateProductVariantDTO);

        if (productVariantDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productVariantDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/productVariant/{id}")
    public ResponseEntity<?> deleteProductVariant(@PathVariable UUID id) {
        productVariantService.deleteProductVariant(id);

        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }
    @GetMapping("/productVariant/{id}")
    public ResponseEntity<ProductVariantDTO> getProductVariantById(@PathVariable UUID id) {

        ProductVariantDTO productVariantDTO = productVariantService.getProductVariant(id);

        if (productVariantDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productVariantDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/productVariant")
    public ResponseEntity<?> getAllProductVariants() {

        List<ProductVariantDTO> productVariantDTOs = productVariantService.getAllProductVariants();

        return ResponseEntity.status(HttpStatus.OK).body(productVariantDTOs);
    }
}
