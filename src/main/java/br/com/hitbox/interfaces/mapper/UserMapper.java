package br.com.hitbox.interfaces.mapper;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.interfaces.request.UserRequest;
import br.com.hitbox.interfaces.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toDomain(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .lastname(request.getLastname())
                .build();
    }

    public UserResponse toResponse(User domain) {
        return UserResponse.builder().build();
    }

}
