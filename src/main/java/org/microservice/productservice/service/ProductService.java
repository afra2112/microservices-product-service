package org.microservice.productservice.service;

import lombok.RequiredArgsConstructor;
import org.microservice.productservice.controller.dto.ProductPurchaseRequest;
import org.microservice.productservice.controller.dto.ProductPurchaseResponse;
import org.microservice.productservice.controller.dto.ProductRequest;
import org.microservice.productservice.controller.dto.ProductResponse;
import org.microservice.productservice.controller.mapper.ProductMapper;
import org.microservice.productservice.entity.Product;
import org.microservice.productservice.exception.ProductNotFoundException;
import org.microservice.productservice.exception.ProductPurchaseException;
import org.microservice.productservice.persistence.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Long createProduct(ProductRequest request) {
        return productRepository.save(productMapper.requestToEntity(request)).getProductId();
    }

    public ProductResponse findById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::entityToResponse)
                .orElseThrow(
                        () -> new ProductNotFoundException("Product not found by given id: " + productId)
                );
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        return requests
                .stream()
                .map(request -> {
                    Product product = productRepository.findById(request.productId()).orElseThrow(
                            () -> new ProductNotFoundException("Product not found by given id: " + request.productId())
                    );

                    if (product.getAvailableQuantity() < request.quantity()){
                        throw new ProductPurchaseException("Insufficient stock quantity for product with id: " + request.productId());
                    }

                    product.setAvailableQuantity(product.getAvailableQuantity() - request.quantity());
                    productRepository.save(product);

                    return new ProductPurchaseResponse(product.getProductId(), product.getName(), product.getDescription(), product.getPrice(), request.quantity());
                }).toList();
    }
}
