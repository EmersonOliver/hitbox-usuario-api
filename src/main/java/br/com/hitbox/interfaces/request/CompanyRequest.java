package br.com.hitbox.interfaces.request;

import br.com.hitbox.infra.enums.CompanyPlanType;
import br.com.hitbox.infra.enums.DocumentType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    private String companyName;

    private String tradeName;

    private String document;

    private DocumentType documentType;

    private String email;

    private String phone;

    private CompanyPlanType planType;
}
