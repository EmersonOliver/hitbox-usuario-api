package br.com.hitbox.interfaces.request;

public record CompleteRegistrationRequest( String token,
                                           String name,
                                           String lastname,
                                           String password) {
}
