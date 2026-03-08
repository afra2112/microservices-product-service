package org.microservice.productservice.exception;

public record ValidationError(
        String field,
        String message
) {
}
