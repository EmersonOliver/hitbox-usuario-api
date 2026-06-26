package br.com.hitbox.infra.mapper;


import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.infra.entity.*;
import br.com.hitbox.interfaces.request.CompanyMembershipRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CompanyMembershipEntityMapper {

    public CompanyMembership toDomain(
            CompanyMembershipEntity entity
    ) {

        return CompanyMembership.builder()
                .membershipId(entity.getId())
                .userId(entity.getUser().getId())
                .companyId(entity.getCompany().getId())
                .companyName(entity.getCompany().getCompanyName())
                .teamName(entity.getTeam().getTeamName())
                .teamId(
                        entity.getTeam() != null
                                ? entity.getTeam().getId()
                                : null
                )
                .build();
    }

    public CompanyMembershipEntity toEntity(CompanyMembership domain) {
        return CompanyMembershipEntity.builder()
                .user(UserEntity.builder().id(domain.getMembershipId()).build())
                .company(CompanyEntity.builder().id(domain.getCompanyId()).build())
                .team(TeamEntity.builder().id(domain.getTeamId()).build())
                .joinedAt(LocalDateTime.now())
                .active(domain.getActive())
                .build();
    }

    public CompanyMembershipRequest requestToDomain(
            CompanyMembershipRequestEntity entity
    ) {

        return CompanyMembershipRequest.builder()
                .requestId(entity.getId())
                .companyId(entity.getCompany().getId())
                .teamId(
                        entity.getTeam() != null
                                ? entity.getTeam().getId()
                                : null
                )
                .email(entity.getEmail())
                .requestedAt(entity.getRequestedAt())
                .status(entity.getStatus())
                .expiresAt(entity.getExpiresAt())
                .build();
    }
}
