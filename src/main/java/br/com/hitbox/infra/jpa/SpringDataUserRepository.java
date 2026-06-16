package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID>,
        JpaSpecificationExecutor<UserEntity> {


    Optional<UserEntity> findByEmail(String email);

    @Query("select u from UserEntity u where u.email = :username")
    UserDetails findByUsername(@Param("username") String username);
}
