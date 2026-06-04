package br.com.hitbox.interfaces.response;

import br.com.hitbox.core.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    @JsonIgnoreProperties("password")
    private User user;
    private String token;


}
