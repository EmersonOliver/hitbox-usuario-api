package br.com.hitbox.interfaces.request;

import br.com.hitbox.infra.enums.UserRole;

import java.util.UUID;

public record InviteMemberRequest(String email,
                                  UUID companyId,
                                  UUID teamId,
                                  UserRole userRole) {
}
