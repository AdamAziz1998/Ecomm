package com.azizONeill.product.dto.convert;


import com.azizONeill.product.dto.ProductDTO;
import com.azizONeill.product.dto.ProductVariantDTO;
import com.azizONeill.product.model.Product;
import com.azizONeill.product.model.ProductVariant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter {
    private final ModelMapper modelMapper;

    public DTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO convertProductToProductDTO (Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product convertProductDTOToProduct (ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    public ProductVariantDTO convertProductVariantToProductVariantDTO(ProductVariant productVariant) {
        return modelMapper.map(productVariant, ProductVariantDTO.class);
    }
}
