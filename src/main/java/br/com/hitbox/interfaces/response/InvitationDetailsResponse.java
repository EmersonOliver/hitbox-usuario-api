package br.com.hitbox.interfaces.response;

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

    private Boolean expired;
}
