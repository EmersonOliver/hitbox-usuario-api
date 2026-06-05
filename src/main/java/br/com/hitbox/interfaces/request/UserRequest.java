package br.com.hitbox.interfaces.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String name;

    private String lastname;

    private String email;

    private String password;
}
