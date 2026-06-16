package br.com.hitbox.infra.entity;

import br.com.hitbox.infra.enums.CompanyPlanType;
import br.com.hitbox.infra.enums.DocumentType;
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
        name = "company",
        indexes = {
                @Index(
                        name = "idx_company_name",
                        columnList = "company_name"
                )
        }
)
public class CompanyEntity {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "trade_name", length = 150)
    private String tradeName;

    private String document;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type", nullable = false, length = 30)
    private CompanyPlanType planType;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<CompanyMembershipEntity> memberships =
            new ArrayList<>();


    @Column(name = "slug", unique = true)
    private String slug;

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