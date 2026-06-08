package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.domain.User;
import br.com.hitbox.infra.enums.UserRole;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.infra.service.TokenService;
import br.com.hitbox.interfaces.response.OnboardingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateCompanyOnboardingUseCase {

    private final UserUseCase userUseCase;
    private final CompanyUseCase companyUseCase;
    private final TeamUseCase teamUseCase;

    private final TokenService tokenService;

    //    @Transactional
    public OnboardingResponse createCompany(
            UUID userId,
            Company company) {


        User user =
                userUseCase.findUserById(userId);


        if (user.getCompanyId() != null) {
            throw new HitboxException(
                    "Usuário já pertence a uma empresa"
            );
        }

        Company createdCompany =
                companyUseCase.create(company);

        String token =
                tokenService.generateToken(
                        user,
                        createdCompany
                );
        Team adminTeam =
                teamUseCase.createDefaultTeam(
                        createdCompany.getCompanyId()
                );

        user.setCompanyId(
                createdCompany.getCompanyId()
        );

        user.setTeamId(
                adminTeam.getTeamId()
        );

        user.setRole(
                UserRole.OWNER
        );

        user.setActive(true);

        userUseCase.update(
                user.getUserId(),
                user
        );

        return new OnboardingResponse(company, token);
    }
}
