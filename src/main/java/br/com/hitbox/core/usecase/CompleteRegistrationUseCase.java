package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.gateway.CompanyMembershipGateway;
import br.com.hitbox.core.gateway.CompanyMembershipRequestGateway;
import br.com.hitbox.core.gateway.UserGateway;
import br.com.hitbox.infra.enums.RequestStatus;
import br.com.hitbox.infra.enums.UserRole;
import br.com.hitbox.infra.enums.UserStatus;
import br.com.hitbox.infra.service.TokenService;
import br.com.hitbox.interfaces.request.CompanyMembershipRequest;
import br.com.hitbox.interfaces.request.CompleteRegistrationRequest;
import br.com.hitbox.interfaces.response.CompleteRegistrationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompleteRegistrationUseCase {

    private final UserGateway userGateway;

    private final CompanyMembershipGateway membershipGateway;

    private final CompanyMembershipRequestGateway requestGateway;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;


    @Transactional
    public CompleteRegistrationResponse complete(
            CompleteRegistrationRequest request
    ) {

        CompanyMembershipRequest invitation =
                requestGateway.findByToken(
                        request.token()
                ).orElseThrow();

        User user = User.builder()
                .name(request.name())
                .lastname(request.lastname())
                .password(
                        passwordEncoder.encode(
                                request.password()
                        )
                )
                .email(tokenService.getEmail(request.token()))
                .active(Boolean.TRUE)
                .role(UserRole.MANAGER)
                .status(UserStatus.ACTIVE)
                .emailVerified(Boolean.TRUE)
                .firstLogin(Boolean.TRUE)

                .build();
        user = userGateway.save(user);

        membershipGateway.save(
                CompanyMembership.builder()
                        .userId(user.getUserId())
                        .companyId(invitation.getCompanyId())
                        .teamId(invitation.getTeamId())
                        .role(invitation.getRole())
                        .active(Boolean.TRUE)
                        .build()
        );

        invitation.setStatus(
                RequestStatus.APPROVED
        );

        requestGateway.update(
                invitation
        );

        return CompleteRegistrationResponse.builder()
                .userId(user.getUserId())
                .companyId(invitation.getCompanyId())
                .teamId(invitation.getTeamId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .success(true)
                .message("Cadastro realizado com sucesso")
                .build();
    }
}
