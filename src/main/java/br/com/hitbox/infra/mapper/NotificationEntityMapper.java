package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.Notification;
import br.com.hitbox.core.domain.NotificationRead;
import br.com.hitbox.infra.entity.CompanyEntity;
import br.com.hitbox.infra.entity.NotificationEntity;
import br.com.hitbox.infra.entity.NotificationReadEntity;
import br.com.hitbox.infra.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationEntityMapper {


    public Notification toDomain(NotificationEntity entity) {
        return Notification.builder()
                .notificationId(entity.getId())
                .companyId(entity.getCompany().getId())
                .title(entity.getTitle())
                .message(entity.getMessage())
                .type(entity.getType())
                .read(!entity.getReads().isEmpty())
                .build();
    }

    public NotificationEntity toEntity(Notification domain) {
        return NotificationEntity.builder()
                .id(domain.getNotificationId())
                .company(CompanyEntity.builder().id(domain.getCompanyId()).build())
                .title(domain.getTitle())
                .message(domain.getMessage())
                .createdAt(domain.getCreateAt())
                .type(domain.getType())
                .build();
    }

    public NotificationRead toReadDomain(NotificationReadEntity entity) {
        return NotificationRead.builder()
                .read(entity.getReadAt() != null)
                .notificationId(entity.getNotification().getId())
                .userId(entity.getUser().getId())
                .companyId(entity.getNotification().getCompany().getId())
                .build();
    }

    public NotificationReadEntity toReadEntity(NotificationRead domain) {
        return NotificationReadEntity.builder()
                .user(UserEntity.builder().id(domain.getUserId()).build())
                .notification(NotificationEntity.builder().id(domain.getNotificationId())
                        .company(CompanyEntity.builder().id(domain.getCompanyId()).build())
                        .build())
                .readAt(LocalDateTime.now())
                .build();
    }

}
