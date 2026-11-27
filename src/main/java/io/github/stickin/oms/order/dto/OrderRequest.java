package io.github.stickin.oms.order.dto;

import io.github.stickin.oms.order.model.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderRequest(
        @NotNull UUID customerId,
        @NotNull OrderStatus status,
        BigDecimal totalAmount,
        @Valid List<OrderItemRequest> items
) {
}
