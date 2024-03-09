package com.azizONeill.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subcategory")
public class Subcategory {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "subcategory_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "subcategory")
    private List<Product> products;
}
