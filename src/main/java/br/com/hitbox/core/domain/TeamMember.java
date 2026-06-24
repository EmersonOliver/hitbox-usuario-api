package br.com.hitbox.core.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TeamMember {

    private UUID memberId;


    private LocalDateTime joinedAt;


}
