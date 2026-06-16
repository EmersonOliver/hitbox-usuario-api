package br.com.hitbox.interfaces.request;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamRequest {

    private UUID companyId;
    private String teamName;
    private String description;
    private Boolean active;

}
