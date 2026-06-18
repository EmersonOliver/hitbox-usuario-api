package br.com.hitbox.interfaces.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CompanySummaryResponse {

    private UUID companyId;

    private String companyName;

    private String slug;
}
