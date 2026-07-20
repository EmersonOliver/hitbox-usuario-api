package br.com.hitbox.core.usecase;

import br.com.hitbox.core.domain.Notification;
import br.com.hitbox.core.domain.NotificationRead;
import br.com.hitbox.core.domain.events.LowStockMovement;
import br.com.hitbox.core.gateway.NotificationGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationUseCase {

    private final NotificationGateway notificationGateway;
    private final SimpMessagingTemplate messagingTemplate;


    @Transactional
    public Notification createNotification(LowStockMovement payload) {
        var buildedNotify = Notification.builder()
                .companyId(payload.getCompanyId())
                .title("Estoque Baixo")
                .type("LOW_STOCK")
                .createAt(payload.getOccurredAt())
                .message(String.format(
                        "%s está abaixo do estoque mínimo. Atual: %s %s",
                        payload.getInventoryName(),
                        payload.getCurrentQuantity(),
                        payload.getUnit()
                ))
                .build();
        return notificationGateway.save(buildedNotify);
    }

    public void sendToAdmins(List<String> admins, List<Notification> notificacoes) {
        messagingTemplate.convertAndSend(
                "/queue/notifications",
                notificacoes
        );

    }

    public void readNotification(NotificationRead domain) {
        this.notificationGateway.read(domain);
    }
}
