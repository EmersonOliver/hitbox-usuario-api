package br.com.hitbox.infra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "event_notification")
public class NotificationEntity {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private String type;

    @OneToMany(mappedBy = "notification", orphanRemoval = true, cascade = CascadeType.ALL)
    @Builder.Default
    private List<NotificationReadEntity> reads = new ArrayList<>();


}
