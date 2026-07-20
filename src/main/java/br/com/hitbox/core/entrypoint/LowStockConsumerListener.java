package br.com.hitbox.core.entrypoint;

import br.com.hitbox.core.domain.events.LowStockMovement;
import br.com.hitbox.core.usecase.NotificationUseCase;
import br.com.hitbox.infra.query.UserQueryService;
import br.com.hitbox.infra.query.projection.UserNotifyProjection;
import br.com.hitbox.infra.service.UserContextService;
import br.com.kafka.util.core.domain.MessageProcessor;
import br.com.kafka.util.core.domain.MessageWrapper;
import br.com.kafka.util.core.gateway.KafkaControl;
import br.com.kafka.util.infra.annotations.TopicKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicKey(value = "low-stock", payload = LowStockMovement.class)
public class LowStockConsumerListener implements MessageProcessor {

    private final ObjectMapper objectMapper;
    private final NotificationUseCase useCase;
    private final UserQueryService userQueryService;

    @Override
    public void process(MessageWrapper msg, KafkaControl control) {
        try {
            var json = objectMapper.writeValueAsString(msg.getPayload());
            var payload = objectMapper.readValue(json, LowStockMovement.class);
            var notify = useCase.createNotification(payload);
            log.info("Notificação gravada com sucesso! Titulo: {}, mensagem {}",
                    notify.getTitle(), notify.getMessage());

            List<UserNotifyProjection> admins = this.userQueryService.listAllIdsByUsersAdminAndManagerByCompany(payload.getCompanyId());
            var adminIds = admins.stream().map(UserNotifyProjection::getUserId).map(UUID::toString).toList();
            useCase.sendToAdmins(adminIds, List.of(notify));
        } catch (Exception e) {
            control.rollback(e);
        } finally {
            control.commit();
        }
    }
}
