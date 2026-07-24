package br.com.hitbox.core.aggregate;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PermissionAggregate {
    private UUID modulePermissionId;

    private String code;

    private String name;

    private boolean granted;
}
