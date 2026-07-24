package br.com.hitbox.infra.jpa;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.infra.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SpringDataTeamRepository extends JpaRepository<TeamEntity, UUID>, JpaSpecificationExecutor<TeamEntity> {

    @Query("select t from TeamEntity t where t.company.id=:companyId")
    List<TeamEntity> listAllTeamByCompanyId(@Param("companyId") UUID companyId);
}
