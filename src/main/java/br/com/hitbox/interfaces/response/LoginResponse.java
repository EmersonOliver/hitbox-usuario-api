package br.com.hitbox.interfaces.response;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private UUID userId;
    private String userName;
    private String email;
    private Boolean firstLogin;
    private Boolean onboardingPending;

    private String token;
    private String temporaryToken;

    private List<CompanySelectionResponse> companies;

}
