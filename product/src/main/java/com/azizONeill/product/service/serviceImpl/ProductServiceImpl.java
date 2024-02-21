package com.azizONeill.product.service.serviceImpl;


import com.azizONeill.product.dto.CreateProductDTO;
import com.azizONeill.product.dto.UpdateProductDTO;
import com.azizONeill.product.dto.convert.ProductConverter;
import com.azizONeill.product.dto.ProductDTO;
import com.azizONeill.product.model.Product;
import com.azizONeill.product.model.enums.Category;
import com.azizONeill.product.repository.ProductRepository;
import com.azizONeill.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(productConverter::convertProductToProductDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            return productConverter.convertProductToProductDTO(product);
        }

        return null;
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);

        return products.stream().map(productConverter::convertProductToProductDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsBySearch(String searchTerm) {
        List<Product> products = productRepository.findBySearchTerm(searchTerm);

        return products.stream().map(productConverter::convertProductToProductDTO).collect(Collectors.toList());
    }

    //code below will be useful for the back office for this application

    @Override
    public ProductDTO createProduct(CreateProductDTO createProductDTO) {

        Product newProduct = new Product();

        newProduct.setName(createProductDTO.getName());
        newProduct.setStatus(createProductDTO.getStatus());
        newProduct.setPrice(createProductDTO.getPrice());
        newProduct.setStockQuantity(createProductDTO.getStockQuantity());
        newProduct.setImageUrl(createProductDTO.getImageUrl());
        newProduct.setDescription(createProductDTO.getDescription());
        newProduct.setCategory(createProductDTO.getCategory());

        newProduct = productRepository.save(newProduct);

        return productConverter.convertProductToProductDTO(newProduct);
    }

    @Override
    public ProductDTO updateProduct(UUID productId, UpdateProductDTO updateProductDTO) {

        Product updatedProduct = productRepository.findById(productId).orElse(null);

        if (updatedProduct == null) {
            return null;
        }

        updatedProduct.setName(updateProductDTO.getName());
        updatedProduct.setStatus(updateProductDTO.getStatus());
        updatedProduct.setPrice(updateProductDTO.getPrice());
        updatedProduct.setStockQuantity(updateProductDTO.getStockQuantity());
        updatedProduct.setImageUrl(updateProductDTO.getImageUrl());
        updatedProduct.setDescription(updateProductDTO.getDescription());
        updatedProduct.setCategory(updateProductDTO.getCategory());

        productRepository.save(updatedProduct);

        return productConverter.convertProductToProductDTO(updatedProduct);
    }

    @Override
    public ProductDTO deleteProduct(UUID productId) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        productRepository.deleteById(productId);

        return productConverter.convertProductToProductDTO(product);
    }
}
