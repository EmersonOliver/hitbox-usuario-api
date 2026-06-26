package br.com.hitbox.infra.config;

import br.com.hitbox.security.AuthenticatedUser;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

@Configuration
//@EnableCaching
public class RedisConfig {

    @Bean
    public ObjectMapper objectMapper() {
            return JsonMapper.builder()
                .findAndAddModules()
                .build();
    }

    @Bean
    public RedisTemplate<String, AuthenticatedUser> redisTemplate(
            RedisConnectionFactory connectionFactory,
            ObjectMapper objectMapper
    ) {

        RedisTemplate<String, AuthenticatedUser> template =
                new RedisTemplate<>();

        template.setConnectionFactory(
                connectionFactory
        );



        JacksonJsonRedisSerializer<Object> serializer =
                new JacksonJsonRedisSerializer<>(
                        objectMapper,
                        Object.class
                );

        template.setKeySerializer(
                new StringRedisSerializer()
        );

        template.setValueSerializer(
                serializer
        );

        template.setHashKeySerializer(
                new StringRedisSerializer()
        );

        template.setHashValueSerializer(
                serializer
        );

        template.afterPropertiesSet();

        return template;
    }
}
