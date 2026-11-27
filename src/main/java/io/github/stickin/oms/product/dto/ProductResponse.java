package io.github.stickin.oms.product.dto;

import io.github.stickin.oms.product.model.ProductStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        BigDecimal price,
        String sku,
        ProductStatus status
) {
}
