package io.github.stickin.oms.inventory.dto;

import java.util.UUID;

public record StockItemResponse(
        UUID id,
        UUID productId,
        int availableQuantity,
        int reservedQuantity
) {
}
