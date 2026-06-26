package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_membership_request")
public class CompanyMembershipRequestEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String invitationToken;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime requestedAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime approvedAt;
}
