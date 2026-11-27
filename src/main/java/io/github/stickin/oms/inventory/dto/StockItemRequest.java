package io.github.stickin.oms.inventory.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record StockItemRequest(
        @NotNull UUID productId,
        @PositiveOrZero int availableQuantity,
        @PositiveOrZero int reservedQuantity
) {
}
