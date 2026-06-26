package br.com.hitbox.interfaces.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanySelectionResponse   {

    private UUID companyId;

    private String companyName;

    private UUID teamId;

    private String teamName;

}
