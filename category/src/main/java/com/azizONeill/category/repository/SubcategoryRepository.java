package com.azizONeill.category.repository;

import com.azizONeill.category.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubcategoryRepository extends JpaRepository<Subcategory, UUID> {
}
