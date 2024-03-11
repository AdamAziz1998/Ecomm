package com.azizONeill.category.dto.convert;


import com.azizONeill.category.dto.CategoryDTO;
import com.azizONeill.category.dto.SubcategoryDTO;
import com.azizONeill.category.model.Category;
import com.azizONeill.category.model.Subcategory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter {
    private final ModelMapper modelMapper;

    public DTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDTO convertCategoryToCategoryDTO (Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public SubcategoryDTO convertSubcategoryToSubcategoryDTO (Subcategory subcategory) {
        return modelMapper.map(subcategory, SubcategoryDTO.class);
    }
}
