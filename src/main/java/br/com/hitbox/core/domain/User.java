package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.UserRole;
import br.com.hitbox.infra.enums.UserStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID userId;

    private UUID companyId;

    private UUID teamId;

    private String name;

    private String lastname;

    private String email;

    private String password;

    private UserRole role;

    private UserStatus status;

    private Boolean active;

    private Boolean emailVerified;

    private Boolean firstLogin;

    private String avatarUrl;

}
