package br.com.hitbox.core.aggregate;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModulePermissionsAggregate {

    private UUID moduleId;

    private String moduleCode;

    private String moduleName;

    private List<PermissionAggregate> permissions;
}
