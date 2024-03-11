package com.azizONeill.category.service;



import com.azizONeill.category.dto.CreateSubcategoryDTO;
import com.azizONeill.category.dto.ProductDTO;
import com.azizONeill.category.dto.SubcategoryDTO;
import com.azizONeill.category.dto.UpdateSubcategoryDTO;

import java.util.List;
import java.util.UUID;

public interface SubcategoryService {
    SubcategoryDTO createSubcategory(CreateSubcategoryDTO createSubcategoryDTO);

    SubcategoryDTO getSubcategoryById(UUID subcategoryId);

    List<SubcategoryDTO> getAllSubcategories();

    SubcategoryDTO updateSubcategory(UUID subcategoryId, UpdateSubcategoryDTO updateSubcategoryDTO);

    void deleteSubcategory(UUID subcategoryId);

    List<ProductDTO> getProductsBySubcategory(UUID subcategoryId);
}
