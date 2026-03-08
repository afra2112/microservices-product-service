package org.microservice.productservice.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.microservice.productservice.controller.dto.ProductRequest;
import org.microservice.productservice.controller.dto.ProductResponse;
import org.microservice.productservice.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "category.description", target = "categoryDescription")
    ProductResponse entityToResponse(Product product);

    Product requestToEntity(ProductRequest request);
}
