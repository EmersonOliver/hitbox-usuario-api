package br.com.hitbox.infra.jpa;

import br.com.hitbox.infra.entity.NotificationReadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataNotificationReadRepository extends JpaRepository<NotificationReadEntity, UUID> {

    List<NotificationReadEntity> findByUserId(UUID userId);


}
