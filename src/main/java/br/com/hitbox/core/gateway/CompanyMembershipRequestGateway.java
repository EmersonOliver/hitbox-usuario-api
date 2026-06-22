package br.com.hitbox.core.gateway;

import br.com.hitbox.interfaces.request.CompanyMembershipRequest;

import java.util.Optional;

public interface CompanyMembershipRequestGateway {

    CompanyMembershipRequest save(
            CompanyMembershipRequest request
    );

    Optional<CompanyMembershipRequest> findByToken(
            String token
    );

    Optional<CompanyMembershipRequest> findPendingByEmail(
            String email
    );

    void update(
            CompanyMembershipRequest request
    );
}
