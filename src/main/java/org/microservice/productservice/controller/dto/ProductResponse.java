package org.microservice.productservice.controller.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long productId,
        String name,
        String description,
        Integer availableQuantity,
        BigDecimal price,
        Long categoryId,
        String categoryName,
        String categoryDescription
) {
}
