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
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        ProductDTO createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/find/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID productId) {
        ProductDTO foundedManager = productService.getDtoById(productId);
        return new ResponseEntity<>(foundedManager, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID productId, @RequestBody ProductDTO productDTO){
        ProductDTO updatedProduct = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable UUID productId){
        productService.deleteProduct(productId);
    }
}
