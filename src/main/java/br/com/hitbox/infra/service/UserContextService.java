package br.com.hitbox.infra.service;


import br.com.hitbox.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserContextService {

    private final RedisTemplate<String, AuthenticatedUser> redisTemplate;

    public void save(
            AuthenticatedUser user
    ) {

        String key =
                buildKey(
                        user.getUserId(),
                        user.getCompanyId()
                );

        redisTemplate.opsForValue()
                .set(
                        key,
                        user,
                        Duration.ofHours(24)
                );
    }

    public AuthenticatedUser find(
            UUID userId,
            UUID companyId
    ) {

        String key =
                buildKey(
                        userId,
                        companyId
                );

        return (AuthenticatedUser)
                redisTemplate.opsForValue()
                        .get(key);
    }

    public void evict(
            UUID userId,
            UUID companyId
    ) {

        redisTemplate.delete(
                buildKey(
                        userId,
                        companyId
                )
        );
    }

    private String buildKey(
            UUID userId,
            UUID companyId
    ) {

        return "user-context:"
                + userId
                + ":"
                + companyId;
    }
}
