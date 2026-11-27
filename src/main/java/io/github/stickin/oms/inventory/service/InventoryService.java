package io.github.stickin.oms.inventory.service;

import io.github.stickin.oms.inventory.dto.StockItemRequest;
import io.github.stickin.oms.inventory.dto.StockItemResponse;
import io.github.stickin.oms.inventory.mapper.StockItemMapper;
import io.github.stickin.oms.inventory.model.StockItem;
import io.github.stickin.oms.inventory.repository.StockItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final StockItemRepository stockItemRepository;
    private final StockItemMapper stockItemMapper;

    public StockItemResponse create(final StockItemRequest request) {
        final StockItem stockItem = stockItemMapper.toEntity(request);
        return stockItemMapper.toResponse(stockItemRepository.save(stockItem));
    }

    @Transactional(readOnly = true)
    public StockItemResponse findById(final UUID id) {
        return stockItemMapper.toResponse(getStockItem(id));
    }

    @Transactional(readOnly = true)
    public List<StockItemResponse> findAll() {
        return stockItemRepository.findAll().stream()
                .map(stockItemMapper::toResponse)
                .toList();
    }

    public StockItemResponse update(final UUID id, final StockItemRequest request) {
        final StockItem stockItem = getStockItem(id);
        stockItemMapper.updateEntity(request, stockItem);
        return stockItemMapper.toResponse(stockItemRepository.save(stockItem));
    }

    public void delete(final UUID id) {
        stockItemRepository.delete(getStockItem(id));
    }

    private StockItem getStockItem(final UUID id) {
        return stockItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock item not found: " + id));
    }
}
