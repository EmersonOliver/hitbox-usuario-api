package br.com.hitbox.interfaces.request;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamPermissionItemRequest {
    private UUID permissionId;
    private String permissionCode;
    private String permissionName;
    private UUID teamPermissionId;
    private Boolean granted;
}
