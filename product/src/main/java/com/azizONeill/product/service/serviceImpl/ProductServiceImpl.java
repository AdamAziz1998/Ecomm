package com.azizONeill.product.service.serviceImpl;

import com.productService.product.convert.ProductConverter;
import com.productService.product.dto.ProductDTO;
import com.productService.product.model.Product;
import com.productService.product.model.enums.Category;
import com.productService.product.repository.ProductRepository;
import com.productService.product.service.ProductService;
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
        log.info("getAllProducts started");
        List<Product> products = productRepository.findAll();
        log.info("getAllProducts len: " + products.size());

        return products.stream().map(productConverter::convertProductToProductDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(UUID productId) {
        log.info("getProductById started");
        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            return productConverter.convertProductToProductDTO(product);
        }

        return null;
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Category category) {
        log.info("getProductsByCategory started");
        List<Product> products = productRepository.findByCategory(category);
        log.info("getProductsByCategory len: " + products.size());

        return products.stream().map(productConverter::convertProductToProductDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsBySearch(String searchTerm) {
        log.info("getProductsBySearch started");
        List<Product> products = productRepository.findBySearchTerm(searchTerm);
        log.info("getProductsBySearch len: " + products.size());

        return products.stream().map(productConverter::convertProductToProductDTO).collect(Collectors.toList());
    }

    //code below will be useful for the back office for this application

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        log.info("createProduct started");

        Product newProduct = new Product();

        newProduct.setName(productDTO.getName());
        newProduct.setStatus(productDTO.getStatus());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setStockQuantity(productDTO.getStockQuantity());
        newProduct.setImageUrl(productDTO.getImageUrl());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setCategory(productDTO.getCategory());

        log.info("createProduct before save id: " + newProduct.getId());

        newProduct = productRepository.save(newProduct);

        log.info("createProduct after save id: " + newProduct.getId());

        return productConverter.convertProductToProductDTO(newProduct);
    }

    @Override
    public ProductDTO updateProduct(UUID productId, ProductDTO productDTO) {

        log.info("updateProduct started");

        Product updatedProduct = productRepository.findById(productId).orElse(null);

        if (updatedProduct == null) {
            return null;
        }

        updatedProduct.setName(productDTO.getName());
        updatedProduct.setStatus(productDTO.getStatus());
        updatedProduct.setPrice(productDTO.getPrice());
        updatedProduct.setStockQuantity(productDTO.getStockQuantity());
        updatedProduct.setImageUrl(productDTO.getImageUrl());
        updatedProduct.setDescription(productDTO.getDescription());
        updatedProduct.setCategory(productDTO.getCategory());

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
