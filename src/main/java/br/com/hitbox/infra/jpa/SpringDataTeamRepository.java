package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataTeamRepository extends JpaRepository<TeamEntity, UUID> {
}
