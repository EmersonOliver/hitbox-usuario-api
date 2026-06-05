package br.com.hitbox.core.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private UUID teamId;

    private UUID companyId;

    private String teamName;

    private String description;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }

    public boolean hasDescription() {
        return description != null
                && !description.isBlank();
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException(
                    "Nome do time é obrigatório"
            );
        }
        this.teamName = newName.trim();
    }
}
