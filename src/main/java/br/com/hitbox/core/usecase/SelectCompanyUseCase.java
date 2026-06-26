package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.gateway.CompanyMembershipGateway;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.infra.service.PermissionService;
import br.com.hitbox.infra.service.TokenService;
import br.com.hitbox.infra.service.UserContextService;
import br.com.hitbox.interfaces.request.SelectCompanyRequest;
import br.com.hitbox.interfaces.response.SelectCompanyResponse;
import br.com.hitbox.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SelectCompanyUseCase {

    private final UserUseCase userUseCase;
    private final CompanyUseCase companyUseCase;
    private final CompanyMembershipGateway membershipGateway;
    private final PermissionService permissionService;
    private final UserContextService userContextService;
    private final TokenService tokenService;

    public SelectCompanyResponse execute(SelectCompanyRequest request) {

        User user = userUseCase.findUserById(request.userId());
        Company company = companyUseCase.findById(request.companyId());
        var membership =
                membershipGateway
                        .findByUserAndCompany(
                                user.getUserId(),
                                company.getCompanyId()
                        )
                        .orElseThrow(() ->
                                new HitboxException(
                                        "Usuário não pertence à empresa"
                                )
                        );
        Set<String> permissions =
                permissionService.loadPermissions(
                        membership.getTeamId()
                );

        AuthenticatedUser authenticatedUser =
                AuthenticatedUser.builder()
                        .userId(user.getUserId())
                        .companyId(company.getCompanyId())
                        .teamId(membership.getTeamId())
                        .companyName(company.getCompanyName())
                        .teamName(membership.getTeamName())
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .userRole(user.getRole())
                        .permissions(permissions)
                        .build();

        userContextService.save(
                authenticatedUser
        );

        String token =
                tokenService.generateToken(
                        authenticatedUser
                );


        return SelectCompanyResponse
                .builder()
                .token(token)
                .userId(user.getUserId())
                .userName(user.getFullName())
                .companyId(company.getCompanyId())
                .companyName(company.getCompanyName())
                .build();
    }

    public List<SelectCompanyResponse> loadCompanyByUser(UUID userId) {

        var membership = membershipGateway.findByUserId(userId);
        return membership.stream().map(res-> SelectCompanyResponse.builder()
                .userId(userId)
                .companyId(res.getCompanyId())
                .userName(res.getUserName())
                .companyName(res.getCompanyName())
                .build()).toList();
    }




}
