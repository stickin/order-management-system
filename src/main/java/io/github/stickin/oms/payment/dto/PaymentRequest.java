package io.github.stickin.oms.payment.dto;

import io.github.stickin.oms.payment.model.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
        @NotNull UUID orderId,
        @NotNull @Positive BigDecimal amount,
        @NotNull PaymentStatus status,
        String transactionId
) {
}
