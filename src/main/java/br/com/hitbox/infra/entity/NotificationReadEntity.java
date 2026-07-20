package br.com.hitbox.infra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification_read")
public class NotificationReadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "notification_read_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private NotificationEntity notification;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDateTime readAt;


}
