package br.com.hitbox.interfaces.response;

import br.com.hitbox.core.domain.Company;
import lombok.Builder;

@Builder
public record OnboardingResponse(Company company,
                                 String token) {
}
