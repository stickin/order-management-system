package io.github.stickin.oms.inventory.repository;

import io.github.stickin.oms.inventory.model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockItemRepository extends JpaRepository<StockItem, UUID> {
}
