package br.com.hitbox.interfaces.response;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamProfileResponse {
    private UUID id;
    private String name;
}
