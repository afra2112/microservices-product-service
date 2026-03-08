package org.microservice.productservice.controller.dto;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
