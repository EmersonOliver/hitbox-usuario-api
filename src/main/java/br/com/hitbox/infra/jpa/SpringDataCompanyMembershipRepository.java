package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.CompanyMembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataCompanyMembershipRepository extends JpaRepository<
        CompanyMembershipEntity,
        UUID> {

    List<CompanyMembershipEntity> findByUserId(
            UUID userId
    );

    Optional<CompanyMembershipEntity>
    findByUserIdAndCompanyId(
            UUID userId,
            UUID companyId
    );
}
