package com.azizONeill.product.service.serviceImpl;

import com.azizONeill.product.dto.CreateProductVariantDTO;
import com.azizONeill.product.dto.ProductVariantDTO;
import com.azizONeill.product.dto.UpdateProductVariantDTO;
import com.azizONeill.product.dto.convert.DTOConverter;
import com.azizONeill.product.model.ProductVariant;
import com.azizONeill.product.repository.ProductVariantRepository;
import com.azizONeill.product.service.ProductVariantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final DTOConverter DTOConverter;

    @Autowired
    public ProductVariantServiceImpl(
            ProductVariantRepository productVariantRepository,
            DTOConverter DTOConverter
    ) {
        this.productVariantRepository = productVariantRepository;
        this.DTOConverter = DTOConverter;
    }

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

        ProductVariant newProductVariant = productVariantRepository.save(productVariant);

        return DTOConverter.convertProductVariantToProductVariantDTO(newProductVariant);
    }

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

    public void deleteProductVariant(UUID productVariantId) {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId).orElse(null);

        if (productVariant == null) {
            //throw error here
        }

        productVariantRepository.delete(productVariant);
    }

    public ProductVariantDTO getProductVariant(UUID productVariantId) {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId).orElse(null);

        if (productVariant == null) {
            return null;
        }

        return DTOConverter.convertProductVariantToProductVariantDTO(productVariant);
    }

    public List<ProductVariantDTO> getAllProductVariants() {
        List<ProductVariant> subcategories = productVariantRepository.findAll();

        return subcategories.stream().map(DTOConverter::convertProductVariantToProductVariantDTO).toList();
    }
}
