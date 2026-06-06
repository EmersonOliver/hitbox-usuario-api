package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.domain.User;
import br.com.hitbox.infra.enums.UserRole;
import br.com.hitbox.infra.exceptions.HitboxException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateCompanyOnboardingUseCase {

    private final UserUseCase userUseCase;
    private final CompanyUseCase companyUseCase;
    private final TeamUseCase teamUseCase;

//    @Transactional
    public Company createCompany(
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

        return createdCompany;
    }
}
