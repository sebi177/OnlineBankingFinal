package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.ProductDTO;
import com.example.onlinebankingfinal.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product);

    Product getById(UUID productId);

    List<Product> getAllProducts();

    Product updateProduct(UUID productId, ProductDTO productDTO);

    void deleteProduct(UUID productId);
}
