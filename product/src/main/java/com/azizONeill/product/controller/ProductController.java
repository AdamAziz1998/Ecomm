package com.azizONeill.product.controller;


import com.azizONeill.product.dto.ProductDTO;
import com.azizONeill.product.model.enums.Category;
import com.azizONeill.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//TODO: Add some security to the project, and some permission for more admin related roles, when the user service is created

@RestController
@RequestMapping("/api/v1")
@Slf4j
@Tag(name="Products API")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    @Operation(summary = "Retrieve All Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found zero or more Products",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))) }),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> getAllProducts() {

        log.info("getProducts started");

        List<ProductDTO> products = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/products/{id}")
    @Operation(summary = "Retrieve Product by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Product matching this Id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Product Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> getProductById(@Parameter(description = "id of Product to be found") @PathVariable UUID id) {

        log.info("getProductById started");

        ProductDTO productDTO = productService.getProductById(id);

        if (productDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @GetMapping("/productsByCategory/{category}")
    @Operation(summary = "Retrieve Products by Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found zero or more Products",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))) }),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@Parameter(description = "Category of the products to be found") @PathVariable Category category) {

        log.info("getProductsByCategory started");

        List<ProductDTO> productDTOList = productService.getProductsByCategory(category);

        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @GetMapping("/productsBySearch/{searchTerm}")
    @Operation(summary = "Retrieve Products by Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found zero or more Products",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))) }),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<List<ProductDTO>> getProductsBySearch(@Parameter(description = "Products found by search term") @PathVariable String searchTerm) {

        log.info("getProductsBySearch started");

        List<ProductDTO> productDTOList = productService.getProductsBySearch(searchTerm);

        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @PostMapping("/products")
    @Operation(summary = "Create New Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created new Product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Failed to Create new Product",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> createProduct(@Parameter(description = "New Product Body Content to be created") @Valid @RequestBody ProductDTO newProductDTO) {

        log.info("createProduct newProductDTO: " + newProductDTO);

        ProductDTO productDTO = productService.createProduct(newProductDTO);

        log.info("createProduct Completed");

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PutMapping("/products/{id}")
    @Operation(summary = "Update Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated Product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Failed to Update Product",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product Id does not exist",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> updateProduct(
            @Parameter(description = "Product Id to be updated") @PathVariable UUID id,
            @Parameter(description = "Product Elements/Body Content to be updated") @Valid @RequestBody ProductDTO updatedProductDTO) {

        log.info("updateProduct started");

        ProductDTO productDTO = productService.updateProduct(id, updatedProductDTO);

        if (productDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @DeleteMapping("/products/{id}")
    @Operation(summary = "Delete Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted Product",
                    content = { @Content() }),
            @ApiResponse(responseCode = "400", description = "Failed to Delete Product",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product Id does not exist",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> deleteProduct(@Parameter(description = "Product Id to be deleted") @PathVariable UUID id) {
        // Delete the id

        log.info("deleteProduct Started");

        ProductDTO productDTO = productService.deleteProduct(id);

        if (productDTO!= null) {
            return ResponseEntity.status(HttpStatus.OK).body("{}");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}