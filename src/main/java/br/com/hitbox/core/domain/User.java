package br.com.hitbox.core.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private UUID userId;
    private String username;
    private String email;
    private String password;
}
