package br.com.hitbox.interfaces.response;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectCompanyResponse {

    private String token;

    private UUID companyId;

    private String companyName;

    private UUID userId;

    private String userName;
}
