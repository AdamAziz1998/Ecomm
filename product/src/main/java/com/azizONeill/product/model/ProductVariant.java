package com.azizONeill.product.model;

import com.azizONeill.product.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

@Entity
@Table(name = "ProductVariant")
public class ProductVariant {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "product_variant_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "status", columnDefinition = "integer")
    private Status status;

    @NotNull
    @Positive
    @Column(name = "price", precision = 10)
    private double price;

    @NotNull
    @PositiveOrZero
    @Column(name = "stockQuantity")
    private int stockQuantity;

    @NotNull
    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "flavour")
    private String flavour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Product product;

    public ProductVariant(UUID id, Status status, double price, int stockQuantity, String imageUrl, String color, String size, String flavour, Product product) {
        this.id = id;
        this.status = status;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.color = color;
        this.size = size;
        this.flavour = flavour;
        this.product = product;
    }

    public ProductVariant() {
    }

    public UUID getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
