package br.com.hitbox.interfaces.request;

import java.util.UUID;

public record SelectCompanyRequest(UUID userId,
                                   UUID companyId) {
}
