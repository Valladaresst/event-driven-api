package com.example.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank String name,
        @NotNull @DecimalMin("0.00") BigDecimal price,
        @NotNull @Min(0) Integer stock
) {}

