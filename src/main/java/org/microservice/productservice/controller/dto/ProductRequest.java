package org.microservice.productservice.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Long productId,

        @NotBlank
        String name,

        @NotBlank
        String description,

        @Positive
        Integer availableQuantity,

        @Positive
        BigDecimal price,

        @NotNull
        Long categoryId
) {
}
