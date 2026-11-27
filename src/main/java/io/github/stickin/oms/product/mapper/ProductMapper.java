package io.github.stickin.oms.product.mapper;

import io.github.stickin.oms.product.dto.ProductRequest;
import io.github.stickin.oms.product.dto.ProductResponse;
import io.github.stickin.oms.product.model.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", defaultValue = "ACTIVE")
    Product toEntity(ProductRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(ProductRequest request, @MappingTarget Product product);
}
