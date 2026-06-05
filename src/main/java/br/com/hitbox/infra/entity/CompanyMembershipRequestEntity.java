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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private CompanyEntity company;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;
}
