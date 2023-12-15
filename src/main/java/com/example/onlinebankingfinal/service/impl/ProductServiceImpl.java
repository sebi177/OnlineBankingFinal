package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.controller.handler.ResponseExceptionHandler;
import com.example.onlinebankingfinal.dto.ProductDTO;
import com.example.onlinebankingfinal.dto.ProductFullDTO;
import com.example.onlinebankingfinal.mapper.ProductMapper;
import com.example.onlinebankingfinal.model.Manager;
import com.example.onlinebankingfinal.model.Product;
import com.example.onlinebankingfinal.model.enums.ProductStatus;
import com.example.onlinebankingfinal.repository.ProductRepository;
import com.example.onlinebankingfinal.service.ManagerService;
import com.example.onlinebankingfinal.service.ProductService;
import com.example.onlinebankingfinal.service.exception.AccountNotFoundException;
import com.example.onlinebankingfinal.service.exception.ProductNotFoundException;
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
    private final ManagerService managerService;

    @Override
    public ProductDTO createProduct(ProductFullDTO product){
        Product newProduct = productMapper.toEntity(product);
        return productMapper.toDto(productRepository.save(newProduct));
    }

    @Override
    public Product getById(UUID productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public ProductDTO getDtoById(UUID productId){
        return productMapper.toDto(getById(productId));
    }

    @Override
    public List<ProductDTO> getAllProducts(){
        return productMapper.listToDto(productRepository.findAll());
    }

    @Override
    public ProductDTO updateProduct(UUID productId, ProductDTO productDTO){
        Product existingProduct = getById(productId);
        productMapper.updateProductFromDTO(productDTO, existingProduct);
        return productMapper.toDto(productRepository.save(existingProduct));
    }

    @Override
    public ProductDTO deleteProduct(UUID productId){
        Product existingProduct = getById(productId);
        existingProduct.setProductStatus(ProductStatus.DELETED);
        productRepository.save(existingProduct);
        return productMapper.toDto(existingProduct);
    }

    @Override
    public ProductFullDTO createProductByManager(UUID managerId, Product product){
        Manager thisManager = managerService.getById(managerId);
        product.setManager(thisManager);
        productRepository.save(product);
        return productMapper.toFullDto(product);
    }
}
