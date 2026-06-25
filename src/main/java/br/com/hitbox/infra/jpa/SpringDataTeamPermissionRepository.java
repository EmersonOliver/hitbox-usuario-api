package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.TeamPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface SpringDataTeamPermissionRepository extends JpaRepository<TeamPermissionEntity, UUID> {

    @Query("""
                select mp.code
                from TeamPermissionEntity tp
                join tp.permission mp
                where tp.team.id = :teamId
            """)
    Set<String> findPermissionCodesByTeamId(
            UUID teamId
    );

    List<TeamPermissionEntity> findByTeam_Id(
            UUID teamId
    );

    List<TeamPermissionEntity> findByTeamId(UUID teamId);

    void deleteByTeam_Id(
            UUID teamId
    );
}
