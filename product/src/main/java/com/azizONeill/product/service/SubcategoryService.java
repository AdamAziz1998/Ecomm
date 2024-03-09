package com.azizONeill.product.service;

import com.azizONeill.product.dto.CreateSubcategoryDTO;
import com.azizONeill.product.dto.SubcategoryDTO;
import com.azizONeill.product.dto.UpdateSubcategoryDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface SubcategoryService {
    SubcategoryDTO createSubcategory(CreateSubcategoryDTO createSubcategoryDTO);

    SubcategoryDTO getSubcategoryById(UUID subcategoryId);

    List<SubcategoryDTO> getAllSubcategories();

    SubcategoryDTO updateSubcategory(UUID subcategoryId, UpdateSubcategoryDTO updateSubcategoryDTO);

    void deleteSubcategory(UUID subcategoryID);
}
