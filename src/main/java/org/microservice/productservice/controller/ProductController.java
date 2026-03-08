package org.microservice.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.microservice.productservice.controller.dto.ProductPurchaseRequest;
import org.microservice.productservice.controller.dto.ProductPurchaseResponse;
import org.microservice.productservice.controller.dto.ProductRequest;
import org.microservice.productservice.controller.dto.ProductResponse;
import org.microservice.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Long> createProduct(ProductRequest request){
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(List<ProductPurchaseRequest> requests){
        return ResponseEntity.ok(productService.purchaseProducts(requests));
    }

    @GetMapping("/{producId}")
    public ResponseEntity<ProductResponse> findById(Long productId){
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
}
