package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.gateway.UserGateway;
import br.com.hitbox.interfaces.response.CompanyProfileResponse;
import br.com.hitbox.interfaces.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProfileUseCase {

    private final UserGateway userGateway;


    public UserProfileResponse load(UUID userId) {
        User user =
                userGateway.findById(userId)
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        "Usuário não encontrado"
                                )
                        );

        return UserProfileResponse.builder()
                .id(user.getUserId())
                .name(user.getName())
                .lastname(user.getLastname())
                .fullName(user.getFullName())
                .phone(user.getCompany().getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .company(
                        CompanyProfileResponse.builder()
                                .id(user.getCompany().getCompanyId())
                                .name(user.getCompany().getCompanyName())
                                .build()
                )
                .build();
    }
}
