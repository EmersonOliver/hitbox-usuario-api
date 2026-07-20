package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.UserEntity;
import br.com.hitbox.infra.query.projection.UserNotifyProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID>,
        JpaSpecificationExecutor<UserEntity> {


    Optional<UserEntity> findByEmail(String email);

    @Query("select u from UserEntity u where u.email = :username")
    UserDetails findByUsername(@Param("username") String username);

    @Query("select u.id as userId, c.company.id as companyId, u.name, c.company.companyName " +
            "from UserEntity u join CompanyMembershipEntity c on c.user.id = u.id " +
            "where u.role = 'ADMIN' or u.role = 'OWNER'")
    List<UserNotifyProjection> listAllUsersByProjectionByCompanyId(@Param("companyId") UUID companyId);
}
