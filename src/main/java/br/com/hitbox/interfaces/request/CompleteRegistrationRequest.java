package br.com.hitbox.interfaces.request;

import br.com.hitbox.infra.enums.UserRole;

public record CompleteRegistrationRequest(String token,
                                          String name,
                                          String lastname,
                                          String password,
                                          UserRole userRole) {
}
