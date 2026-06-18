package br.com.hitbox.infra.exceptions;

public class HitboxUnauthorizedException extends RuntimeException{


    public HitboxUnauthorizedException(String message) {
        super(message);
    }
}
