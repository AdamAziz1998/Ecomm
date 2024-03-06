package com.azizONeill.product.service;

import com.azizONeill.product.dto.CreateSubcategoryDTO;
import com.azizONeill.product.dto.SubcategoryDTO;

import java.util.Set;
import java.util.UUID;

public interface SubcategoryService {
    SubcategoryDTO createSubcategory(CreateSubcategoryDTO createSubcategoryDTO);

    SubcategoryDTO getSubcategoryById(UUID subcategoryId);

    Set<SubcategoryDTO> getAllSubcategories();

    SubcategoryDTO updateSubcategory(UpdateSubcategoryDTO updateSubcategoryDTO);

    void deleteSubcategory(UUID subcategoryID);
}
