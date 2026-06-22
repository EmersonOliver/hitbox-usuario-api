package br.com.hitbox.interfaces.request;

import br.com.hitbox.infra.enums.TeamRole;

import java.util.UUID;

public record InviteMemberRequest(String email,
                                  UUID companyId,
                                  UUID teamId,
                                  TeamRole role) {
}
