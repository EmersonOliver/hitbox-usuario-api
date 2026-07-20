package br.com.hitbox.infra.persistence;

import br.com.hitbox.core.domain.Notification;
import br.com.hitbox.core.domain.NotificationRead;
import br.com.hitbox.core.gateway.NotificationGateway;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataNotificationReadRepository;
import br.com.hitbox.infra.jpa.SpringDataNotificationRepository;
import br.com.hitbox.infra.mapper.NotificationEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationPersistenceRepository implements NotificationGateway {

    private final SpringDataNotificationRepository notificationRepository;
    private final SpringDataNotificationReadRepository notificationReadRepository;
    private final NotificationEntityMapper mapper;

    @Override
    public Notification save(Notification domain) {
        var entity = notificationRepository.save(mapper.toEntity(domain));
        return mapper.toDomain(entity);
    }

    @Override
    public NotificationRead read(NotificationRead domain) {
        var notification = notificationRepository.findById(domain.getNotificationId())
                .orElseThrow(() -> new HitboxException("Notificação não encontrada."));
        var entity = notificationReadRepository.save(mapper.toReadEntity(domain));
        entity.setNotification(notification);
        return mapper.toReadDomain(entity);
    }


}
