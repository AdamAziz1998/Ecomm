package com.azizONeill.product.service;


import com.azizONeill.product.dto.CreateProductDTO;
import com.azizONeill.product.dto.ProductDTO;
import com.azizONeill.product.dto.UpdateProductDTO;
import com.azizONeill.product.model.enums.Category;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(UUID productId);

    List<ProductDTO> getProductsByCategory(Category category);

    List<ProductDTO> getProductsBySearch(String searchTerm);

    ProductDTO createProduct(CreateProductDTO createProductDTO);

    ProductDTO updateProduct(UUID productId, UpdateProductDTO updateProductDTO);

    ProductDTO deleteProduct(UUID productId);
}
