package br.com.hitbox.interfaces.request;


import br.com.hitbox.infra.enums.TeamRole;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamRequest {

    private UUID companyId;
    private UUID userId;
    private String teamName;
    private TeamRole teamRole;
    private String description;
    private Boolean active;

}
