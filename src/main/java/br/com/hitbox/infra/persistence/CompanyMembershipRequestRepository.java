package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Company;
import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.gateway.CompanyMembershipRequestGateway;
import br.com.hitbox.infra.entity.CompanyMembershipRequestEntity;
import br.com.hitbox.infra.enums.RequestStatus;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataCompanyMembershipRequestRepository;
import br.com.hitbox.infra.jpa.SpringDataCompanyRepository;
import br.com.hitbox.infra.jpa.SpringDataTeamRepository;
import br.com.hitbox.infra.service.TokenService;
import br.com.hitbox.interfaces.request.CompanyMembershipRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static br.com.hitbox.infra.enums.RequestStatus.APPROVED;

@Repository
@RequiredArgsConstructor
public class CompanyMembershipRequestRepository implements CompanyMembershipRequestGateway {

    private final SpringDataCompanyMembershipRequestRepository repository;
    private final SpringDataCompanyRepository companyRepository;
    private final SpringDataTeamRepository teamRepository;
    private final TokenService tokenService;

    @Override
    public CompanyMembershipRequest save(CompanyMembershipRequest request) {
        UUID requestId = UUID.randomUUID();
        CompanyMembershipRequestEntity entity = CompanyMembershipRequestEntity.builder()
                .id(requestId)
                .requestedAt(LocalDateTime.now())
                .role(request.getRole())
                .expiresAt(LocalDateTime.now().plusHours(1))
                .status(RequestStatus.PENDING)
                .email(request.getEmail())
                .build();
        validate(entity, request.getCompanyId(), request.getTeamId(), request);
        entity = repository.save(entity);
        return CompanyMembershipRequest.builder()
                .requestId(entity.getId())
                .invitationToken(entity.getInvitationToken())
                .requestedAt(entity.getRequestedAt())
                .email(entity.getEmail())
                .status(entity.getStatus())
                .expiresAt(entity.getExpiresAt())
                .teamId(entity.getTeam().getId())
                .companyId(entity.getCompany().getId())
                .role(entity.getRole())
                .status(entity.getStatus())
                .build();
    }

    @Override
    public Optional<CompanyMembershipRequest> findByToken(String token) {
        return repository.findByToken(token).map(rs -> CompanyMembershipRequest.builder()
                .invitationToken(rs.getInvitationToken())
                .email(rs.getEmail())
                .requestedAt(rs.getRequestedAt())
                .requestId(rs.getId())
                .teamId(rs.getTeam().getId())
                .status(rs.getStatus())
                .expiresAt(rs.getExpiresAt())
                .role(rs.getRole())
                .companyId(rs.getCompany().getId())
                .build());
    }

    @Override
    public Optional<CompanyMembershipRequest> findPendingByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void update(CompanyMembershipRequest request) {
        CompanyMembershipRequestEntity entity = repository.findById(request.getRequestId()).
                orElseThrow(() -> new HitboxException("Request not found"));
        entity.setStatus(request.getStatus());
        if (APPROVED.equals(entity.getStatus())) {
            entity.setApprovedAt(LocalDateTime.now());
        }


    }


    private void validate(final CompanyMembershipRequestEntity entity,
                          UUID companyId, UUID teamId, CompanyMembershipRequest request) {
        var company = companyRepository.findById(companyId)
                .orElseThrow(() -> new HitboxException("Company não encontrada ou é inválida!"));
        var team = teamRepository.findById(teamId)
                .orElseThrow(() -> new HitboxException("Team não encontrado ou é inválido!!"));
        entity.setCompany(company);
        entity.setTeam(team);
        var token = tokenService.generateInviteToken(
                entity.getId(),
                Company.builder()
                        .companyId(company.getId())
                        .companyName(company.getCompanyName())
                        .build(),
                Team.builder()
                        .teamId(team.getId())
                        .teamName(team.getTeamName())
                        .build(),
                request);
        entity.setInvitationToken(token);
    }
}
