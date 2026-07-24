package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.TeamPermissionEntity;
import br.com.hitbox.infra.query.projection.TeamPermissionProjection;
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
    @Query("""
        SELECT
            m.id            AS moduleId,
            m.code                AS moduleCode,
            m.name                AS moduleName,

            mp.id AS permissionId,
            mp.code               AS permissionCode,
            mp.name               AS permissionName,

            tp.id   AS teamPermissionId,
            tp.id             AS teamId

        FROM ModuleEntity m

        JOIN ModulePermissionEntity mp
             ON mp.module.id = m.id

        LEFT JOIN TeamPermissionEntity tp
             ON tp.permission.id = mp.id
            AND tp.team.id = :teamId

        ORDER BY m.name, mp.name
    """)
    List<TeamPermissionProjection> findPermissionsByTeam(UUID teamId);


}
