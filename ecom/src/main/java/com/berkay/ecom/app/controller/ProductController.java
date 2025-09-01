package com.berkay.ecom.app.controller;

import com.berkay.ecom.app.dto.product.ProductRequest;
import com.berkay.ecom.app.dto.product.ProductResponse;
import com.berkay.ecom.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody ProductRequest productRequest
            , @PathVariable Long id) {

        return productService.updateProduct(id, productRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
