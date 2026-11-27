package io.github.stickin.oms.inventory.controller;

import io.github.stickin.oms.inventory.dto.StockItemRequest;
import io.github.stickin.oms.inventory.dto.StockItemResponse;
import io.github.stickin.oms.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stock-items")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<StockItemResponse> create(@Valid @RequestBody final StockItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.create(request));
    }

    @GetMapping
    public List<StockItemResponse> findAll() {
        return inventoryService.findAll();
    }

    @GetMapping("/{id}")
    public StockItemResponse findById(@PathVariable final UUID id) {
        return inventoryService.findById(id);
    }

    @PutMapping("/{id}")
    public StockItemResponse update(@PathVariable final UUID id, @Valid @RequestBody final StockItemRequest request) {
        return inventoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
