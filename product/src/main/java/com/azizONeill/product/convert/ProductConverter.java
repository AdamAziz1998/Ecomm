package com.azizONeill.product.convert;


import com.azizONeill.product.dto.ProductDTO;
import com.azizONeill.product.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    private final ModelMapper modelMapper;

    public ProductConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductDTO convertProductToProductDTO (Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product convertProductDTOToProduct (ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
