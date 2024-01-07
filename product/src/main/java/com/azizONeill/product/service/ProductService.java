package com.azizONeill.product.service;

import com.productService.product.dto.ProductDTO;
import com.productService.product.model.enums.Category;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(UUID productId);

    List<ProductDTO> getProductsByCategory(Category category);

    List<ProductDTO> getProductsBySearch(String searchTerm);

    ProductDTO createProduct(ProductDTO product);

    ProductDTO updateProduct(UUID productId, ProductDTO updateProduct);

    ProductDTO deleteProduct(UUID productId);
}
