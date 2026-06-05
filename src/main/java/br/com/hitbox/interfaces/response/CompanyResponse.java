package br.com.hitbox.interfaces.response;
import br.com.hitbox.infra.enums.CompanyPlanType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private UUID id;

    private String companyName;

    private String tradeName;

    private String cnpj;

    private String email;

    private String phone;

    private CompanyPlanType planType;

    private Boolean active;
}
