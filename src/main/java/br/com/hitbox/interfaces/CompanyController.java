package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.CompanyOnboardingUseCase;
import br.com.hitbox.core.usecase.CompanyUseCase;
import br.com.hitbox.core.usecase.SelectCompanyUseCase;
import br.com.hitbox.interfaces.mapper.CompanyMapper;
import br.com.hitbox.interfaces.request.CompanyRequest;
import br.com.hitbox.interfaces.request.SelectCompanyRequest;
import br.com.hitbox.interfaces.response.OnboardingResponse;
import br.com.hitbox.interfaces.response.SelectCompanyResponse;
import br.com.hitbox.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("company")
public class CompanyController {


    private final CompanyUseCase companyUseCase;
    private final CompanyOnboardingUseCase onboardingUseCase;
    private final SelectCompanyUseCase selectCompanyUseCase;
    private final CompanyMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<OnboardingResponse> createCompany(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestBody CompanyRequest request) {
        var company = onboardingUseCase.createCompany(user.getUserId(), mapper.toDomain(request));
        return ResponseEntity.ok(company);
    }

    @PostMapping("/select-company")
    public ResponseEntity<SelectCompanyResponse> selectCompany(
            @RequestBody SelectCompanyRequest request
    ) {
        return ResponseEntity.ok(
                selectCompanyUseCase
                        .execute(request)
        );
    }

    @GetMapping("/user/companys")
    public ResponseEntity<List<SelectCompanyResponse>> loadCompanyByUserId(@RequestParam UUID userId) {
        var response = selectCompanyUseCase.loadCompanyByUser(userId);
        return ResponseEntity.ok(response);

    }
}
