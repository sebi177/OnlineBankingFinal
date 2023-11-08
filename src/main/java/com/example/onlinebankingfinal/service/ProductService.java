package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.ProductDTO;
import com.example.onlinebankingfinal.dto.ProductFullDTO;
import com.example.onlinebankingfinal.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDTO createProduct(Product product);

    Product getById(UUID productId);

    ProductDTO getDtoById(UUID productId);

    List<ProductDTO> getAllProducts();

    ProductDTO updateProduct(UUID productId, ProductDTO productDTO);

    void deleteProduct(UUID productId);

    ProductFullDTO createProductByManager(UUID managerId, Product product);
}
