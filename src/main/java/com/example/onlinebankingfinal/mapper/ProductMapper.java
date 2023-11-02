package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.ProductDTO;
import com.example.onlinebankingfinal.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    void updateProductFromDTO(ProductDTO productDTO, @MappingTarget Product product);

    Product toProduct(ProductDTO productDTO);

    ProductDTO toDto(Product product);

    List<ProductDTO> listToDto(List<Product> productList);
}
