package br.com.hitbox.core.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LowStockMovement {

    private UUID companyId;
    private Long inventoryId;
    private String inventoryName;
    private String categoryName;
    private Double currentQuantity;
    private Double minimumQuantity;
    private String unit;
    private LocalDateTime occurredAt;

}
