package com.azizONeill.product.service.serviceImpl;

import com.azizONeill.product.dto.CreateProductDTO;
import com.azizONeill.product.dto.ProductDTO;
import com.azizONeill.product.dto.UpdateProductDTO;
import com.azizONeill.product.dto.convert.DTOConverter;
import com.azizONeill.product.model.Category;
import com.azizONeill.product.model.Product;
import com.azizONeill.product.model.Subcategory;
import com.azizONeill.product.repository.CategoryRepository;
import com.azizONeill.product.repository.ProductRepository;
import com.azizONeill.product.repository.SubcategoryRepository;
import com.azizONeill.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final DTOConverter DTOConverter;
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            DTOConverter DTOConverter,
            SubcategoryRepository subcategoryRepository,
            CategoryRepository categoryRepository
    ) {
        this.productRepository = productRepository;
        this.DTOConverter = DTOConverter;
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(DTOConverter::convertProductToProductDTO).collect(Collectors.toList());
    }

    @Override
    @Cacheable("productCache")
    public ProductDTO getProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            return DTOConverter.convertProductToProductDTO(product);
        }

        return null;
    }

    @Override
    public List<ProductDTO> getProductsBySearch(String searchTerm) {
        List<Product> products = productRepository.findBySearchTerm(searchTerm);

        return products.stream().map(DTOConverter::convertProductToProductDTO).collect(Collectors.toList());
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

        Subcategory subcategory = subcategoryRepository
                .findById(createProductDTO.getSubcategoryId())
                .orElse(null);

        if (subcategory == null) {
            return null;
        }

        subcategory.getProducts().add(newProduct);
        subcategoryRepository.save(subcategory);

        return DTOConverter.convertProductToProductDTO(newProduct);
    }

    @Override
    @CachePut(value = "productCache", key = "#productId")
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

        productRepository.save(updatedProduct);

        return DTOConverter.convertProductToProductDTO(updatedProduct);
    }

    @Override
    @CacheEvict(value = "productCache", key = "#productId", beforeInvocation = true)
    public ProductDTO deleteProduct(UUID productId) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return null;
        }

        productRepository.deleteById(productId);

        return DTOConverter.convertProductToProductDTO(product);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            return null;
        }

        List<Subcategory> subCategories = category.getSubcategories();
        List<Product> products = new ArrayList<>();

        subCategories.forEach(
                subcategory -> products.addAll(subcategory.getProducts())
        );

        return products.stream().map(DTOConverter::convertProductToProductDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsBySubcategory(UUID subcategoryId) {
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElse(null);

        if (subcategory == null) {
            return null;
        }

        List<Product> products = subcategory.getProducts();

        return products.stream().map(DTOConverter::convertProductToProductDTO).collect(Collectors.toList());
    }
}
