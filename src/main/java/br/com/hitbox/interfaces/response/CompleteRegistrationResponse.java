package br.com.hitbox.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompleteRegistrationResponse {

    private UUID userId;
    private UUID companyId;
    private UUID teamId;
    private String fullName;
    private String email;
    private Boolean success;
    private String message;
}
