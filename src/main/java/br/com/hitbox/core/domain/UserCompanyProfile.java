package br.com.hitbox.core.domain;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCompanyProfile {

    private UUID companyId;
    private String companyName;
    private UUID teamId;
    private String teamName;
}
