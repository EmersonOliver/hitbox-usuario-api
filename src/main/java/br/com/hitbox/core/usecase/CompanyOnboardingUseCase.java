package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.domain.User;
import br.com.hitbox.infra.enums.UserRole;
import br.com.hitbox.infra.service.TokenService;
import br.com.hitbox.interfaces.request.SelectCompanyRequest;
import br.com.hitbox.interfaces.response.OnboardingResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompanyOnboardingUseCase {

    private final UserUseCase userUseCase;
    private final CompanyUseCase companyUseCase;
    private final TeamUseCase teamUseCase;
    private final CompanyMembershipUseCase membershipUseCase;
    private final TeamPermissionUseCase teamPermissionUseCase;
    private final SelectCompanyUseCase selectCompanyUseCase;
    private final TokenService tokenService;

    @Transactional
    public OnboardingResponse createCompany(UUID userId, Company company) {
        User user = userUseCase.findUserById(userId);
        Company createdCompany = companyUseCase.create(company);
        Team ownerTeam = teamUseCase.createDefaultTeam(createdCompany.getCompanyId());
        membershipUseCase.createOwnerMembership(
                user,
                createdCompany,
                ownerTeam
        );

        user.setRole(UserRole.OWNER);
        userUseCase.update(user.getUserId(), user);
        teamPermissionUseCase.grantAllPermissions(ownerTeam.getTeamId());

        var companySelected = selectCompanyUseCase.execute(new SelectCompanyRequest(user.getUserId(), createdCompany.getCompanyId()));
        String token = companySelected.getToken();
        return new OnboardingResponse(
                createdCompany,
                token
        );
    }
}
