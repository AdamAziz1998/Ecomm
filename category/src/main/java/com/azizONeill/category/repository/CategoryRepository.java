package com.azizONeill.category.repository;

import com.azizONeill.category.model.Category;
import com.azizONeill.category.model.enums.SuperCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT c FROM Category c WHERE c.superCategory = ?1")
    List<Category> findBySuperCategory(SuperCategory superCategory);
}
