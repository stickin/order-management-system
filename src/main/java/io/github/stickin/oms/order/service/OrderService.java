package io.github.stickin.oms.order.service;

import io.github.stickin.oms.order.repository.OrderItemRepository;
import io.github.stickin.oms.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(final OrderRepository orderRepository,
                        final OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }
}
