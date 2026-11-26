package io.github.stickin.oms.order.model;

public enum OrderStatus {
    PENDING,
    AWAITING_PAYMENT,
    CONFIRMED,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    PAYMENT_FAILED,
}
