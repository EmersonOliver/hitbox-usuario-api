package br.com.hitbox.security;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserContextCache {
    private UUID userId;

    private UUID companyId;

    private UUID teamId;

    private Set<String> permissions;
}
