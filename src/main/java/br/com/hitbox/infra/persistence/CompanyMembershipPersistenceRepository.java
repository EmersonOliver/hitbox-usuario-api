package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.core.gateway.CompanyMembershipGateway;
import br.com.hitbox.infra.jpa.SpringDataCompanyMembershipRepository;
import br.com.hitbox.infra.mapper.CompanyMembershipEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CompanyMembershipPersistenceRepository implements CompanyMembershipGateway {

    private final SpringDataCompanyMembershipRepository repository;
    private final CompanyMembershipEntityMapper mapper;

    @Override
    public CompanyMembership save(CompanyMembership membership) {
        return null;
    }

    @Override
    public List<CompanyMembership> findByUserId(UUID userId) {

        return repository.findByUserId(userId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<CompanyMembership>
    findByUserAndCompany(
            UUID userId,
            UUID companyId
    ) {

        return repository
                .findByUserIdAndCompanyId(
                        userId,
                        companyId
                )
                .map(mapper::toDomain);
    }
}
