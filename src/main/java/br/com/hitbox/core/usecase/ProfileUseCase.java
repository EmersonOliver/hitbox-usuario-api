package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.gateway.CompanyMembershipGateway;
import br.com.hitbox.core.gateway.UserGateway;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.interfaces.response.CompanyProfileResponse;
import br.com.hitbox.interfaces.response.TeamProfileResponse;
import br.com.hitbox.interfaces.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProfileUseCase {

    private final UserGateway userGateway;

    private final CompanyMembershipGateway membershipGateway;

    public UserProfileResponse load(
            UUID userId,
            UUID companyId
    ) {

        User user =
                userGateway.findById(userId)
                        .orElseThrow(() ->
                                new HitboxException(
                                        "Usuário não encontrado"
                                ));

        CompanyMembership membership =
                membershipGateway
                        .findByUserAndCompany(userId, companyId)
                        .orElseThrow(() ->
                                new HitboxException(
                                        "Membership não encontrado"
                                ));

        return UserProfileResponse.builder()
                .id(user.getUserId())
                .name(user.getName())
                .lastname(user.getLastname())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .active(user.getActive())
                .role(user.getRole())
                .teamRole(membership.getRole())
                .company(CompanyProfileResponse.builder()
                        .id(membership.getCompanyId())
                        .name(membership.getCompanyName())
                        .build()
                ).team(TeamProfileResponse.builder()
                        .id(membership.getTeamId())
                        .name(membership.getTeamName())
                        .build()
                )
                .datetimeLastLogin(user.getLastLogin())
                .build();
    }
}