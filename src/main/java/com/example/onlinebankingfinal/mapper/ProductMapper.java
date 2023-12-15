package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.ProductDTO;
import com.example.onlinebankingfinal.dto.ProductFullDTO;
import com.example.onlinebankingfinal.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    void updateProductFromDTO(ProductDTO productDTO, @MappingTarget Product product);

    @Mapping(source = "manager",target = "manager.managerId")
    Product toEntity(ProductFullDTO productFullDTO);

    ProductDTO toDto(Product product);

    List<ProductDTO> listToDto(List<Product> productList);

    @Mapping(source = "manager.managerId",target = "manager")
    ProductFullDTO toFullDto(Product product);

    @Mapping(source = "manager.managerId",target = "manager")
    List<ProductFullDTO> listToFullDto(List<Product> productList);
}
