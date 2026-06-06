    package br.com.hitbox.interfaces.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

    @Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String user;
    private String token;
    private String email;
    private UUID companyId;
    private Boolean firstLogin;

}
