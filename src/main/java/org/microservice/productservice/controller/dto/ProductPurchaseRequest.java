package org.microservice.productservice.controller.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

        @NotNull
        Long productId,

        @NotNull
        Integer quantity
) {
}
