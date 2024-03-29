package com.azizONeill.product.service.serviceImpl;

import com.azizONeill.product.config.exceptions.notFound.ResourceNotFoundException;
import com.azizONeill.product.dto.CreateProductDTO;
import com.azizONeill.product.dto.ProductDTO;
import com.azizONeill.product.dto.ProductVariantDTO;
import com.azizONeill.product.dto.UpdateProductDTO;
import com.azizONeill.product.dto.convert.DTOConverter;
import com.azizONeill.product.model.Product;
import com.azizONeill.product.repository.ProductRepository;
import com.azizONeill.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final DTOConverter DTOConverter;



    @Override
    public List<ProductDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();
        return products.stream().map(DTOConverter::convertProductToProductDTO).collect(Collectors.toList());
    }

    @Override
    @Cacheable("productCache")
    public ProductDTO getProductById(UUID productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + productId));
        return DTOConverter.convertProductToProductDTO(product);
    }

    @Override
    public List<ProductDTO> getProductsBySearch(String searchTerm) {

        List<Product> products = productRepository.findBySearchTerm(searchTerm);
        return products.stream().map(DTOConverter::convertProductToProductDTO).collect(Collectors.toList());
    }

    //code below will be useful for the back office for this application

    @Override
    public ProductDTO createProduct(CreateProductDTO createProductDTO) {

        Product product = new Product();
        product.setName(createProductDTO.getName());
        product.setDisplayPrice(createProductDTO.getDisplayPrice());
        product.setImageUrl(createProductDTO.getImageUrl());
        product.setDescription(createProductDTO.getDescription());
        Product newProduct = productRepository.save(product);
        return DTOConverter.convertProductToProductDTO(newProduct);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "subcategory", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "productCartCache", allEntries = true, beforeInvocation = true)
            },
            put = {@CachePut(value = "productCache", key = "#productId")}
    )
    public ProductDTO updateProduct(UUID productId, UpdateProductDTO updateProductDTO) {

        Product updatedProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + productId));
        updatedProduct.setName(updateProductDTO.getName());
        updatedProduct.setDisplayPrice(updateProductDTO.getDisplayPrice());
        updatedProduct.setImageUrl(updateProductDTO.getImageUrl());
        updatedProduct.setDescription(updateProductDTO.getDescription());
        productRepository.save(updatedProduct);
        return DTOConverter.convertProductToProductDTO(updatedProduct);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "productCache", key = "#productId", beforeInvocation = true),
            @CacheEvict(value = "productVariant", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "subcategory", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "productCartCache", allEntries = true, beforeInvocation = true),
    })
    public ProductDTO deleteProduct(UUID productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + productId));
        productRepository.deleteById(productId);
        return DTOConverter.convertProductToProductDTO(product);
    }

    @Cacheable("productVariant")
    public List<ProductVariantDTO> getProductVariantByProductId(UUID productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + productId));
        return product.getProductVariants().stream().map(DTOConverter::convertProductVariantToProductVariantDTO).toList();
    }
}
