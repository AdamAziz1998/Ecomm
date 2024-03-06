package com.azizONeill.product.repository;

import com.azizONeill.product.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubcategoryRepository extends JpaRepository<Subcategory, UUID> {
}
