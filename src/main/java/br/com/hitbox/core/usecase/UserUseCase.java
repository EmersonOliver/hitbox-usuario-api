package br.com.hitbox.core.usecase;

import br.com.hitbox.core.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUseCase {

    private final UserGateway userGateway;


}
