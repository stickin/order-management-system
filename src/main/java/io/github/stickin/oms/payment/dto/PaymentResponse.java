package io.github.stickin.oms.payment.dto;

import io.github.stickin.oms.payment.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentResponse(
        UUID id,
        UUID orderId,
        BigDecimal amount,
        PaymentStatus status,
        String transactionId,
        Instant createdAt
) {
}
