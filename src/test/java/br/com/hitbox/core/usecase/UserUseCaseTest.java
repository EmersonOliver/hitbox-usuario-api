package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.gateway.UserGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {


    @Test
    void test_resetPassword() {
        UserGateway userGateway = mock(UserGateway.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        UserUseCase useCase = new UserUseCase(userGateway, passwordEncoder);
        UUID id = UUID.randomUUID();
        when(userGateway.findById(id)).thenReturn(Optional.of(User.builder()
                .userId(id)
                .email("teste@teste.com")
                .name("Emerson")
                .firstLogin(true)
                .password("")
                .build()));
        String result = useCase.resetPassword(id);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Emerson@2026", result);
    }


}