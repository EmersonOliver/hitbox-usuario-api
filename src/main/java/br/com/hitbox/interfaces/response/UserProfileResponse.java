package br.com.hitbox.interfaces.response;

import br.com.hitbox.infra.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileResponse {

    private UUID id;

    private String name;

    private String lastname;

    private String fullName;

    private String email;

    private String avatarUrl;

    private Boolean active;

    private UserRole role;


    private CompanyProfileResponse company;

    private TeamMemberResponse team;

    private LocalDateTime datetimeLastLogin;
}
