package br.com.hitbox.core.domain;

import br.com.hitbox.infra.enums.TeamRole;
import br.com.hitbox.interfaces.request.CompanyMembershipRequest;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private UUID teamId;
    private UUID companyId;
    private String teamName;
    private String description;
    private Boolean active;
    private TeamRole teamRole;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer totalMembers;

    @Builder.Default
    private List<CompanyMembership> memberships =
            new ArrayList<>();

    @Builder.Default
    private List<CompanyMembershipRequest> pendingInvitations =
            new ArrayList<>();

    public Integer countTotalMembers() {
        return memberships.size();
    }

    public void addMember(
            UUID userId,
            TeamRole role,
            UUID companyId
    ) {

        boolean alreadyExists =
                memberships.stream()
                        .anyMatch(m ->
                                m.getUserId()
                                        .equals(userId)
                        );

        if (alreadyExists) {

            throw new IllegalArgumentException(
                    "Usuário já pertence ao time"
            );
        }

        memberships.add(
                CompanyMembership.builder()
                        .userId(userId)
                        .companyId(companyId)
                        .teamId(teamId)
                        .role(role)
                        .joinedAt(LocalDateTime.now())
                        .active(Boolean.TRUE)
                        .build()
        );
    }

    public void removeMember(UUID memberId) {
        memberships.removeIf(member ->
                member.getMembershipId()
                        .equals(memberId)
        );
    }

    public void changeMemberRole(
            UUID memberId,
            TeamRole role
    ) {

        CompanyMembership companyMembership =
                memberships.stream()
                        .filter(m ->
                                m.getMembershipId()
                                        .equals(memberId)
                        )
                        .findFirst()
                        .orElseThrow();

        companyMembership.changeRole(role);
    }

    public boolean containsMember(UUID memberId) {
        return memberships.stream()
                .anyMatch(member ->
                        member.getMembershipId()
                                .equals(memberId)
                );
    }

    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }

    public boolean hasDescription() {
        return description != null
                && !description.isBlank();
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException(
                    "Nome do time é obrigatório"
            );
        }
        this.teamName = newName.trim();
    }
}
