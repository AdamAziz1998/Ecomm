package com.azizONeill.product.controller;


import com.azizONeill.product.dto.CreateProductDTO;
import com.azizONeill.product.dto.ProductDTO;
import com.azizONeill.product.dto.UpdateProductDTO;
import com.azizONeill.product.service.ProductService;
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
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts() {

        List<ProductDTO> products = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id) {

        ProductDTO productDTO = productService.getProductById(id);

        if (productDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @GetMapping("/product/search/{searchTerm}")
    public ResponseEntity<List<ProductDTO>> getProductsBySearch(@PathVariable String searchTerm) {

        List<ProductDTO> productDTOList = productService.getProductsBySearch(searchTerm);

        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {

        ProductDTO productDTO = productService.createProduct(createProductDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @Valid @RequestBody UpdateProductDTO updateProductDTO) {

        ProductDTO productDTO = productService.updateProduct(id, updateProductDTO);

        if (productDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
        ProductDTO productDTO = productService.deleteProduct(id);

        if (productDTO!= null) {
            return ResponseEntity.status(HttpStatus.OK).body("{}");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable UUID categoryId) {

        List<ProductDTO> productDTOList = productService.getProductsByCategory(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @GetMapping("/product/subCategory/{subcategory}")
    public ResponseEntity<List<ProductDTO>> getProductsBySubcategory(@PathVariable UUID subcategoryId) {

        List<ProductDTO> productDTOList = productService.getProductsBySubcategory(subcategoryId);

        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }
}