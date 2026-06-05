package br.com.hitbox.interfaces.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private UUID userId;
    private UUID teamId;
    private UUID companyId;
    private String fullName;
    private String name;
    private String lastName;
    private String email;
    private String avatarUrl;

}
