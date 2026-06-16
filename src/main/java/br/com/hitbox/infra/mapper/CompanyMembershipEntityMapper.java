package br.com.hitbox.infra.mapper;


import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.infra.entity.CompanyMembershipEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMembershipEntityMapper {

    public CompanyMembership toDomain(
            CompanyMembershipEntity entity
    ) {

        return CompanyMembership.builder()
                .membershipId(entity.getId())
                .userId(entity.getUser().getId())
                .companyId(entity.getCompany().getId())
                .teamId(
                        entity.getTeam() != null
                                ? entity.getTeam().getId()
                                : null
                )
                .build();
    }
}
