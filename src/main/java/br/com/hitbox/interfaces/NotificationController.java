package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.Notification;
import br.com.hitbox.core.domain.NotificationRead;
import br.com.hitbox.core.gateway.NotificationGateway;
import br.com.hitbox.core.usecase.NotificationUseCase;
import br.com.hitbox.infra.query.NotificationQueryService;
import br.com.hitbox.interfaces.request.NotificationReadRequest;
import br.com.hitbox.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationQueryService queryService;
    private final NotificationUseCase notificationUseCase;

    @GetMapping("all")
    public ResponseEntity<Page<Notification>> getAllNotifications(Pageable pageable, @AuthenticationPrincipal AuthenticatedUser user) {
        var result = queryService.allNotifications(pageable, user.getCompanyId());
        var result2 = queryService.allNotificationsProjection(user.getCompanyId(), user.getUserId());

        return ResponseEntity.ok(result);
    }

    @GetMapping("v2/all")
    public ResponseEntity<List<Notification>> getV2AllNotifications(@AuthenticationPrincipal AuthenticatedUser user) {
        var result2 = queryService.allNotificationsProjection(user.getCompanyId(), user.getUserId())
                .stream().map(rs -> Notification.builder()
                        .title(rs.getTitle())
                        .message(rs.getMessage())
                        .createAt(rs.getCreatedAt())
                        .notificationId(rs.getId())
                        .read(rs.getRead())
                        .build())
                .toList();
        return ResponseEntity.ok(result2);
    }

    @PostMapping("read")
    public ResponseEntity<NotificationRead> readMessage(@RequestBody NotificationReadRequest request,
                                                        @AuthenticationPrincipal AuthenticatedUser user) {
        var readerNotify = NotificationRead.builder()
                .read(request.read())
                .notificationId(request.notificationId())
                .userId(user.getUserId())
                .companyId(user.getCompanyId())
                .build();
        notificationUseCase.readNotification(readerNotify);
        return ResponseEntity.ok().build();
    }
}
