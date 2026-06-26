package br.com.hitbox.infra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "company_membership",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_company_membership",
                        columnNames = {
                                "user_id",
                                "company_id"
                        }
                )
        }
)
public class CompanyMembershipEntity {

    @Id
    @Column(name = "company_membership_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "company_id",
            nullable = false
    )
    private CompanyEntity company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "team_id",
            nullable = false
    )
    private TeamEntity team;



    @Column(nullable = false)
    private LocalDateTime joinedAt;

    @Column(nullable = false)
    private Boolean active;

    @PrePersist
    public void prePersist() {

        if (joinedAt == null) {
            joinedAt = LocalDateTime.now();
        }

        if (active == null) {
            active = Boolean.TRUE;
        }
    }


}
