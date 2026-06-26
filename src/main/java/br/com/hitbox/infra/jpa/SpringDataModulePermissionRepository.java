package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.ModulePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataModulePermissionRepository extends JpaRepository<ModulePermissionEntity, UUID> {
}
