package br.com.hitbox.core.domain;

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
