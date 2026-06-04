package br.com.hitbox.infra.mapper;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.domain.UserProfile;
import br.com.hitbox.infra.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserEntityMapper {


    public UserEntity toEntity(User domain) {

        if (domain == null) {
            return null;
        }

        UserEntity entity = new UserEntity();

        entity.setId(domain.getUserId());
        entity.setName(domain.getName());
        entity.setLastname(domain.getLastname());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setRole(domain.getRole());
        entity.setActive(domain.getActive());

        return entity;
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return User.builder()
                .userId(entity.getId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .role(entity.getRole())
                .active(entity.getActive())
                .avatarUrl(entity.getAvatarUrl())
                .firstLogin(entity.getFirstLogin())
                .companyId(
                        entity.getCompany() != null
                                ? entity.getCompany().getId()
                                : null
                )
                .teamId(
                        entity.getTeam() != null
                                ? entity.getTeam().getId()
                                : null
                )
                .build();
    }

    public UserProfile toUserProfile(UserEntity entity) {

        if (entity == null) {
            return null;
        }

        return UserProfile.builder()
                .userId(entity.getId())

                .fullName(entity.getFullName())

                .email(entity.getEmail())

                .avatarUrl(entity.getAvatarUrl())

                .role(entity.getRole())

                .companyName(
                        entity.getCompany() != null
                                ? entity.getCompany().getCompanyName()
                                : null
                )

                .teamName(
                        entity.getTeam() != null
                                ? entity.getTeam().getTeamName()
                                : null
                )

                .active(entity.getActive())

                .lastLogin(entity.getLastLogin())

                .build();
    }

    public List<UserProfile> toUserProfiles(
            List<UserEntity> entities) {

        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toUserProfile)
                .toList();
    }

    public void updateEntity(
            User source,
            UserEntity target) {

        target.setName(source.getName());
        target.setLastname(source.getLastname());
        target.setEmail(source.getEmail());
        target.setRole(source.getRole());
        target.setActive(source.getActive());
        target.setAvatarUrl(source.getAvatarUrl());
    }
}
