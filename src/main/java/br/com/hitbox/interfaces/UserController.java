package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.usecase.UserUseCase;
import br.com.hitbox.interfaces.mapper.UserMapper;
import br.com.hitbox.interfaces.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserUseCase userUseCase;
    private final UserMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserRequest request) {
        var result = userUseCase.createFirstLogin(mapper.toDomain(request));
        return ResponseEntity.ok(result);
    }

}
