package br.com.hitbox.interfaces.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinCompanyRequest {
    private UUID userId;

    private String companyName;

    private String cnpj;
}
