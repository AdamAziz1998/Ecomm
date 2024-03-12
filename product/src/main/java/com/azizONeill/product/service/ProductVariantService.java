package com.azizONeill.product.service;

import com.azizONeill.product.dto.CreateProductVariantDTO;
import com.azizONeill.product.dto.ProductVariantDTO;
import com.azizONeill.product.dto.UpdateProductVariantDTO;

import java.util.List;
import java.util.UUID;

public interface ProductVariantService {

    ProductVariantDTO createProductVariant(CreateProductVariantDTO createProductVariantDTO);
    ProductVariantDTO updateProductVariant(UUID productVariantId, UpdateProductVariantDTO updateProductVariantDTO);
    void deleteProductVariant(UUID productVariantId);
    ProductVariantDTO getProductVariant(UUID productVariantId);
    List<ProductVariantDTO> getAllProductVariants();
}
