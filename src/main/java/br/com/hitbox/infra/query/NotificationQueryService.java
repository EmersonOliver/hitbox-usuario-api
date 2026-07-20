package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.Notification;
import br.com.hitbox.infra.jpa.SpringDataNotificationRepository;
import br.com.hitbox.infra.mapper.NotificationEntityMapper;
import br.com.hitbox.infra.query.projection.NotificationProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationQueryService {

    private final SpringDataNotificationRepository repository;
    private final NotificationEntityMapper mapper;


    public Page<Notification> allNotifications(Pageable pageable, UUID companyId) {
        return repository.findByCompanyId(companyId, pageable).map(mapper::toDomain);
    }

    public List<NotificationProjection> allNotificationsProjection(UUID companyId,
                                                                   UUID userId) {
        return repository.findNotifications(companyId, userId);
    }
}
