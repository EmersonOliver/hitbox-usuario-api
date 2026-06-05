package br.com.hitbox.core.domain;
import br.com.hitbox.infra.enums.CompanyPlanType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private UUID companyId;

    private String companyName;

    private String tradeName;

    private String cnpj;

    private String email;

    private String phone;

    private CompanyPlanType planType;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }

    public boolean isEnterprisePlan() {
        return CompanyPlanType.ENTERPRISE.equals(planType);
    }

    public boolean isProfessionalPlan() {
        return CompanyPlanType.PROFESSIONAL.equals(planType);
    }

    public boolean canCreateUnlimitedUsers() {
        return CompanyPlanType.ENTERPRISE.equals(planType)
                || CompanyPlanType.PROFESSIONAL.equals(planType);
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }
}
