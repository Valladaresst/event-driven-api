package com.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 120)
    private String name;

    @NotNull
    @DecimalMin("0.00")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    public Product() {}

    public Product(Long id, String name, BigDecimal price, Integer stock) {
        this.id = id; this.name = name; this.price = price; this.stock = stock;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
