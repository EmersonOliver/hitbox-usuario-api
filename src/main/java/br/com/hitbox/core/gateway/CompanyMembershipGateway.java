package br.com.hitbox.core.gateway;

import br.com.hitbox.core.domain.CompanyMembership;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyMembershipGateway {
    CompanyMembership save(CompanyMembership membership);

    List<CompanyMembership> findByUserId(
            UUID userId
    );

    Optional<CompanyMembership> findByUserAndCompany(
            UUID userId,
            UUID companyId
    );
}
