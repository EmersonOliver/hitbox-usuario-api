package br.com.hitbox.interfaces.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamResponse {
    private UUID teamId;
    private UUID companyId;
    private String teamName;
    private String description;
    private Boolean active;
    private Boolean defaultTeam;
    private Integer totalMembers;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<TeamMemberResponse> members;
}
