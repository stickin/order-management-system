package io.github.stickin.oms.product.dto;

import io.github.stickin.oms.product.model.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank String name,
        @NotNull @Positive BigDecimal price,
        @NotBlank String sku,
        ProductStatus status
) {
}
