package io.github.stickin.oms.inventory.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "oms_stock_item")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockItem {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    private UUID id;
}
