package io.github.stickin.oms.inventory.service;

import io.github.stickin.oms.inventory.repository.StockItemRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final StockItemRepository stockItemRepository;

    public InventoryService(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }
}
