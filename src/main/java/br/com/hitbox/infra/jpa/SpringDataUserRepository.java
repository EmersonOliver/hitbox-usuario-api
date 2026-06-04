package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
    @Query("select c from UserEntity c where c.company.id=:companyId")
    Page<UserEntity> findAllByCompany(Pageable pageable, @Param("companyId") UUID companyId);
}
