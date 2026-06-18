package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.core.gateway.CompanyMembershipGateway;
import br.com.hitbox.infra.entity.CompanyMembershipEntity;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataCompanyMembershipRepository;
import br.com.hitbox.infra.jpa.SpringDataCompanyRepository;
import br.com.hitbox.infra.jpa.SpringDataTeamRepository;
import br.com.hitbox.infra.jpa.SpringDataUserRepository;
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
    private final SpringDataUserRepository userRepository;
    private final SpringDataCompanyRepository companyRepository;
    private final SpringDataTeamRepository teamRepository;

    @Override
    public CompanyMembership save(CompanyMembership membership) {
        CompanyMembershipEntity entity = mapper.toEntity(membership);
        validateRelationship(membership.getUserId(),
                membership.getCompanyId(),
                membership.getTeamId(), entity);
        return mapper.toDomain(repository.save(entity));
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

    private void validateRelationship(
            UUID userId,
            UUID companyId,
            UUID teamId,
            final CompanyMembershipEntity entity) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new HitboxException("Usuário não encontrado!"));
        var company = companyRepository.findById(companyId)
                .orElseThrow(() -> new HitboxException("Company não encontrada!"));
        var team = teamRepository.findById(teamId)
                .orElseThrow(() -> new HitboxException("Equipe não encontrada!"));
        entity.setCompany(company);
        entity.setUser(user);
        entity.setTeam(team);
    }
}
