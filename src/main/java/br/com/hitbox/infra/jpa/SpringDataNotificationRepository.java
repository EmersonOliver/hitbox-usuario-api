package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.NotificationEntity;
import br.com.hitbox.infra.query.projection.NotificationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataNotificationRepository extends JpaRepository<NotificationEntity, UUID> {

    List<NotificationEntity> findByCompanyId(UUID companyId);

    Page<NotificationEntity> findByCompanyId(UUID companyId, Pageable pageable);

    @Query("""
            SELECT
                n.id as id,
                n.title as title,
                n.message as message,
                CASE
                    WHEN nr.id IS NULL THEN false
                    ELSE true
                END as read,
                   n.createdAt as createdAt
            FROM NotificationEntity n
            LEFT JOIN NotificationReadEntity nr
                   ON nr.notification.id = n.id
                  AND nr.user.id = :userId
            WHERE n.company.id = :companyId
                        ORDER BY n.createdAt DESC
            """)
    List<NotificationProjection> findNotifications(
            UUID companyId,
            UUID userId
    );
}
