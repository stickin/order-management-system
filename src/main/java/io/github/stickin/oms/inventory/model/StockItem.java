package io.github.stickin.oms.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(
    name = "oms_stock_item",
    indexes = @Index(name = "idx_stock_item_product_id", columnList = "productId")
)
@Getter
@Setter
@NoArgsConstructor
public class StockItem {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    private UUID id;

    private UUID productId;

    private int availableQuantity;

    private int reservedQuantity;

}
