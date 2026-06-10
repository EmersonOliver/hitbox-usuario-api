package br.com.hitbox.interfaces.response;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyProfileResponse {

    private UUID id;
    private String name;
}
