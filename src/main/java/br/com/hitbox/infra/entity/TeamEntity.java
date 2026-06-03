package br.com.hitbox.infra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "team",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_team_company_name",
                        columnNames = {
                                "company_id",
                                "team_name"
                        }
                )
        },
        indexes = {
                @Index(
                        name = "idx_team_company",
                        columnList = "company_id"
                )
        }
)
public class TeamEntity {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            name = "team_name",
            nullable = false,
            length = 120
    )
    private String teamName;

    @Column(length = 500)
    private String description;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @Column(
            name = "created_at",
            nullable = false
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<UserEntity> members = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "company_id",
            nullable = false
    )
    private CompanyEntity company;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}