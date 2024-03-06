package com.azizONeill.product.dto.convert;


import com.azizONeill.product.dto.CategoryDTO;
import com.azizONeill.product.dto.ProductDTO;
import com.azizONeill.product.dto.SubcategoryDTO;
import com.azizONeill.product.model.Category;
import com.azizONeill.product.model.Product;
import com.azizONeill.product.model.Subcategory;
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

    public CategoryDTO convertCategoryToCategoryDTO (Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public SubcategoryDTO convertSubcategoryToSubcategoryDTO (Subcategory subcategory) {
        return modelMapper.map(subcategory, SubcategoryDTO.class);
    }
}
