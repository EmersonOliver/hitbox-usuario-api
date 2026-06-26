package br.com.hitbox.infra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "module_permission")
public class ModulePermissionEntity {

    @Id
    @Column(name = "module_permission_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private ModuleEntity module;

    private String code;

    private String name;
}
