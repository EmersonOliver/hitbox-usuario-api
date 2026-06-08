package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.CompanyUseCase;
import br.com.hitbox.core.usecase.CreateCompanyOnboardingUseCase;
import br.com.hitbox.interfaces.mapper.CompanyMapper;
import br.com.hitbox.interfaces.request.CompanyRequest;
import br.com.hitbox.interfaces.response.CompanyResponse;
import br.com.hitbox.interfaces.response.LoginResponse;
import br.com.hitbox.interfaces.response.OnboardingResponse;
import br.com.hitbox.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("company")
public class CompanyController {


    private final CompanyUseCase companyUseCase;
    private final CreateCompanyOnboardingUseCase onboardingUseCase;
    private final CompanyMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<OnboardingResponse> createCompany(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestBody CompanyRequest request) {


        var company =
                onboardingUseCase.createCompany(
                        user.getUserId(),
                        mapper.toDomain(request)
                );

        return ResponseEntity.ok(company
        );
    }
}
