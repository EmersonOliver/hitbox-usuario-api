package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.CompanyMembershipRequestEntity;
import br.com.hitbox.interfaces.request.CompanyMembershipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataCompanyMembershipRequestRepository extends JpaRepository<
        CompanyMembershipRequestEntity,
        UUID> {
    @Query("select m from CompanyMembershipRequestEntity m where m.invitationToken =:token")
    Optional<CompanyMembershipRequestEntity> findByToken(@Param("token") String token);
}
