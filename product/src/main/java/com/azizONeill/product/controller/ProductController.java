package com.azizONeill.product.controller;


import com.azizONeill.product.dto.CreateProductDTO;
import com.azizONeill.product.dto.ProductDTO;
import com.azizONeill.product.dto.UpdateProductDTO;
import com.azizONeill.product.model.enums.Category;
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


    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {

        List<ProductDTO> products = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id) {

        ProductDTO productDTO = productService.getProductById(id);

        if (productDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @GetMapping("/products/category/")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@RequestParam(value = "category") String category) {

        List<ProductDTO> productDTOList = productService.getProductsByCategory(Category.valueOf(category));

        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @GetMapping("/products/search/")
    public ResponseEntity<List<ProductDTO>> getProductsBySearch(@RequestParam(value = "search") String searchTerm) {

        List<ProductDTO> productDTOList = productService.getProductsBySearch(searchTerm);

        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {

        ProductDTO productDTO = productService.createProduct(createProductDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @Valid @RequestBody UpdateProductDTO updateProductDTO) {

        ProductDTO productDTO = productService.updateProduct(id, updateProductDTO);

        if (productDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
        ProductDTO productDTO = productService.deleteProduct(id);

        if (productDTO!= null) {
            return ResponseEntity.status(HttpStatus.OK).body("{}");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }
}