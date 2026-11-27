package io.github.stickin.oms.order.mapper;

import io.github.stickin.oms.order.dto.OrderItemRequest;
import io.github.stickin.oms.order.dto.OrderItemResponse;
import io.github.stickin.oms.order.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemResponse toResponse(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemRequest request);
}
