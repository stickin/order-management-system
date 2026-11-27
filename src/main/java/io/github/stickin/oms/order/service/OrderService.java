package io.github.stickin.oms.order.service;

import io.github.stickin.oms.order.dto.OrderRequest;
import io.github.stickin.oms.order.dto.OrderResponse;
import io.github.stickin.oms.order.mapper.OrderItemMapper;
import io.github.stickin.oms.order.mapper.OrderMapper;
import io.github.stickin.oms.order.model.Order;
import io.github.stickin.oms.order.model.OrderItem;
import io.github.stickin.oms.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    public OrderResponse create(final OrderRequest request) {
        final Order order = orderMapper.toEntity(request);
        order.setCreatedAt(Instant.now());
        linkItems(order);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(final UUID id) {
        return orderMapper.toResponse(getOrder(id));
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    public OrderResponse update(final UUID id, final OrderRequest request) {
        final Order order = getOrder(id);
        orderMapper.updateEntity(request, order);
        replaceItems(order, request);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    public void delete(final UUID id) {
        orderRepository.delete(getOrder(id));
    }

    private void linkItems(final Order order) {
        if (order.getItems() == null) {
            order.setItems(new ArrayList<>());
        }
        order.getItems().forEach(item -> item.setOrder(order));
    }

    private void replaceItems(final Order order, final OrderRequest request) {
        order.getItems().clear();
        if (request.items() != null) {
            final List<OrderItem> items = request.items().stream()
                    .map(orderItemMapper::toEntity)
                    .toList();
            items.forEach(item -> item.setOrder(order));
            order.getItems().addAll(items);
        }
    }

    private Order getOrder(final UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id));
    }
}
