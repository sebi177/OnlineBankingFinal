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
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/find/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable UUID productId) {
        return productService.getDtoById(productId);
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/update/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@PathVariable UUID productId, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(productId, productDTO);
    }

    @PostMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO deleteProduct(@PathVariable UUID productId){
        return productService.deleteProduct(productId);
    }
}
