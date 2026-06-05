package br.com.hitbox.interfaces.request;

import br.com.hitbox.infra.enums.CompanyPlanType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    private String companyName;

    private String tradeName;

    private String cnpj;

    private String email;

    private String phone;

    private CompanyPlanType planType;
}
