package br.com.hitbox.infra.config.security;

import java.security.Principal;

public class StompPrincipal implements Principal {

    private final String uuid;

    public StompPrincipal(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getName() {
        return uuid;
    }
}
