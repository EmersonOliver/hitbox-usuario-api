package br.com.hitbox.core.aggregate;

import br.com.hitbox.core.domain.TeamPermissionItem;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamPermissionAggregate {

    private UUID moduleId;

    private String moduleCode;

    private String moduleName;

    private List<TeamPermissionItem> permissions;
}
