package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.ProductDTO;
import com.example.onlinebankingfinal.mapper.ProductMapper;
import com.example.onlinebankingfinal.model.Product;
import com.example.onlinebankingfinal.repository.ProductRepository;
import com.example.onlinebankingfinal.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product getById(UUID productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found!"));
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(UUID productId, ProductDTO productDTO){
        Product existingProduct = getById(productId);
        productMapper.updateProductFromDTO(productDTO, existingProduct);
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID productId){
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        productRepository.delete(existingProduct);
    }
}
