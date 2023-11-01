package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.ProductDTO;
import com.example.onlinebankingfinal.model.Product;
import com.example.onlinebankingfinal.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/find/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID productId) {
        Product foundedManager = productService.getById(productId);
        return new ResponseEntity<>(foundedManager, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID productId, @RequestBody ProductDTO productDTO){
        Product updatedProduct = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable UUID productId){
        productService.deleteProduct(productId);
    }
}
