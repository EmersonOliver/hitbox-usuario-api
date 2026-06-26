package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.gateway.TeamGateway;
import br.com.hitbox.infra.entity.TeamEntity;
import br.com.hitbox.infra.enums.RequestStatus;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataCompanyMembershipRequestRepository;
import br.com.hitbox.infra.jpa.SpringDataTeamRepository;
import br.com.hitbox.infra.mapper.CompanyMembershipEntityMapper;
import br.com.hitbox.infra.mapper.TeamEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamQueryService {

    private final SpringDataTeamRepository teamRepository;
    private final TeamEntityMapper mapper;
    private final SpringDataCompanyMembershipRequestRepository requestRepository;
    private final CompanyMembershipEntityMapper companyMembershipEntityMapper;
    private final TeamGateway teamGateway;

    public Page<Team> listAllTeams(Pageable pageable, UUID companyId) {
        Specification<TeamEntity> specs = (root, query, builder) -> {
            return builder.equal(root.get("company").get("id"), companyId);
        };

        var requestedMembers = requestRepository.findAll().stream()
                .filter(rs-> !rs.getStatus().equals(RequestStatus.APPROVED)
                        && LocalDateTime.now().isBefore(rs.getExpiresAt())).toList();
        var requestsByTeam =
                requestedMembers.stream()
                        .collect(
                                Collectors.groupingBy(
                                        rs -> rs.getTeam().getId()
                                )
                        );
        var teams =
                teamRepository
                        .findAll(specs, pageable)
                        .map(mapper::toDomain)
                        .map(team -> {

                            var invitations =
                                    requestsByTeam
                                            .getOrDefault(
                                                    team.getTeamId(),
                                                    Collections.emptyList()
                                            );

                            team.setPendingInvitations(
                                    invitations.stream()
                                            .map(
                                                    companyMembershipEntityMapper::requestToDomain
                                            )
                                            .toList()
                            );

                            return team;
                        });

        return teams;
    }


    public Team findById(UUID teamId){
        return this.teamGateway.findById(teamId)
                .orElseThrow(()-> new HitboxException("Time Requisitado é inválido"));
    }


}
