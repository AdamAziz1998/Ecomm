package com.azizONeill.product.service.serviceImpl;

import com.azizONeill.product.dto.CreateProductVariantDTO;
import com.azizONeill.product.dto.ProductVariantDTO;
import com.azizONeill.product.dto.UpdateProductVariantDTO;
import com.azizONeill.product.dto.convert.DTOConverter;
import com.azizONeill.product.model.Product;
import com.azizONeill.product.model.ProductVariant;
import com.azizONeill.product.repository.ProductRepository;
import com.azizONeill.product.repository.ProductVariantRepository;
import com.azizONeill.product.service.ProductVariantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final DTOConverter DTOConverter;
    private final ProductRepository productRepository;

    @Autowired
    public ProductVariantServiceImpl(
            ProductVariantRepository productVariantRepository,
            DTOConverter DTOConverter,
            ProductRepository productRepository
    ) {
        this.productVariantRepository = productVariantRepository;
        this.DTOConverter = DTOConverter;
        this.productRepository = productRepository;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "productCache", key = "#createProductVariantDTO.getProductId()", beforeInvocation = true),
            @CacheEvict(value = "productVariant", key = "#productVariantId", beforeInvocation = true),
            @CacheEvict(value = "subcategory", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "productCartCache", allEntries = true, beforeInvocation = true),
    })
    public ProductVariantDTO createProductVariant(CreateProductVariantDTO createProductVariantDTO) {

        ProductVariant productVariant = new ProductVariant();

        productVariant.setStatus(createProductVariantDTO.getStatus());
        productVariant.setPrice(createProductVariantDTO.getPrice());
        productVariant.setStockQuantity(createProductVariantDTO.getStockQuantity());
        productVariant.setImageUrl(createProductVariantDTO.getImageUrl());

        if (createProductVariantDTO.isColorPresent()) {
            productVariant.setColor(createProductVariantDTO.getColor());
        }

        if (createProductVariantDTO.isSizePresent()) {
            productVariant.setSize(createProductVariantDTO.getSize());
        }

        if (createProductVariantDTO.isFlavourPresent()) {
            productVariant.setSize(createProductVariantDTO.getSize());
        }

        Product product = productRepository.findById(createProductVariantDTO.getProductId()).orElse(null);

        if (product == null) {
            return null;
        }

        product.getProductVariants().add(productVariant);
        productRepository.save(product);
        ProductVariant newProductVariant = productVariantRepository.save(productVariant);

        return DTOConverter.convertProductVariantToProductVariantDTO(newProductVariant);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "productCache", key = "#productVariant.getProduct().getId()", beforeInvocation = true),
            @CacheEvict(value = "productVariant", key = "#productVariantId", beforeInvocation = true),
            @CacheEvict(value = "subcategory", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "productCartCache", allEntries = true, beforeInvocation = true),
    })
    public ProductVariantDTO updateProductVariant(UUID productVariantId, UpdateProductVariantDTO updateProductVariantDTO) {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId).orElse(null);

        if (productVariant == null) {
            return null;
        }

        productVariant.setStatus(updateProductVariantDTO.getStatus());
        productVariant.setPrice(updateProductVariantDTO.getPrice());
        productVariant.setStockQuantity(updateProductVariantDTO.getStockQuantity());
        productVariant.setImageUrl(updateProductVariantDTO.getImageUrl());

        if (updateProductVariantDTO.isColorPresent()) {
            productVariant.setColor(updateProductVariantDTO.getColor());
        }

        if (updateProductVariantDTO.isSizePresent()) {
            productVariant.setSize(updateProductVariantDTO.getSize());
        }

        if (updateProductVariantDTO.isFlavourPresent()) {
            productVariant.setSize(updateProductVariantDTO.getSize());
        }

        ProductVariant updatedProductVariant = productVariantRepository.save(productVariant);

        return DTOConverter.convertProductVariantToProductVariantDTO(updatedProductVariant);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "productCache", key = "#productVariant.getProduct().getId()", beforeInvocation = true),
            @CacheEvict(value = "productVariant", key = "#productVariantId", beforeInvocation = true),
            @CacheEvict(value = "subcategory", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "productCartCache", allEntries = true, beforeInvocation = true),
    })
    public void deleteProductVariant(UUID productVariantId) {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId).orElse(null);

        if (productVariant == null) {
            //throw error here
        }

        productVariantRepository.delete(productVariant);
    }

    @Override
    public ProductVariantDTO getProductVariant(UUID productVariantId) {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId).orElse(null);

        if (productVariant == null) {
            return null;
        }

        return DTOConverter.convertProductVariantToProductVariantDTO(productVariant);
    }

    @Override
    public List<ProductVariantDTO> getAllProductVariants() {
        List<ProductVariant> subcategories = productVariantRepository.findAll();

        return subcategories.stream().map(DTOConverter::convertProductVariantToProductVariantDTO).toList();
    }
}
