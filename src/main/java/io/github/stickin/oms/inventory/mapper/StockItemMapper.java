package io.github.stickin.oms.inventory.mapper;

import io.github.stickin.oms.inventory.dto.StockItemRequest;
import io.github.stickin.oms.inventory.dto.StockItemResponse;
import io.github.stickin.oms.inventory.model.StockItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StockItemMapper {

    StockItemResponse toResponse(StockItem stockItem);

    @Mapping(target = "id", ignore = true)
    StockItem toEntity(StockItemRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntity(StockItemRequest request, @MappingTarget StockItem stockItem);
}
