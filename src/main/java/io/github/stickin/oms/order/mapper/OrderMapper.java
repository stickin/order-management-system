package io.github.stickin.oms.order.mapper;

import io.github.stickin.oms.order.dto.OrderRequest;
import io.github.stickin.oms.order.dto.OrderResponse;
import io.github.stickin.oms.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

    OrderResponse toResponse(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Order toEntity(OrderRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "items", ignore = true)
    void updateEntity(OrderRequest request, @MappingTarget Order order);
}
