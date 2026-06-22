package br.com.hitbox.interfaces.response;

import br.com.hitbox.infra.enums.TeamRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvitationDetailsResponse {

    private String email;

    private String companyName;

    private String teamName;

    private TeamRole role;

    private Boolean expired;
}
