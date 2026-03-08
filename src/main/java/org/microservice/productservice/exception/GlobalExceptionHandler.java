package org.microservice.productservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ApiError buildApiError(String message, String path, int status, List<ValidationError> errors){
        return new ApiError(
                message,
                path,
                status,
                errors
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleCustomerNotFound(ProductNotFoundException ex, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildApiError(
                        ex.getMessage(),
                        request.getRequestURI(),
                        HttpStatus.NOT_FOUND.value(),
                        null
                ));
    }

    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<ApiError> handleProductPurchaseException(ProductPurchaseException ex, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildApiError(
                        ex.getMessage(),
                        request.getRequestURI(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        null
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<ValidationError> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildApiError(
                        ex.getMessage(),
                        request.getRequestURI(),
                        HttpStatus.NO_CONTENT.value(),
                        errors
                ));
    }
}
