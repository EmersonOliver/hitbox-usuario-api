package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.core.usecase.UserUseCase;
import br.com.hitbox.infra.enums.UserRole;
import br.com.hitbox.interfaces.mapper.UserMapper;
import br.com.hitbox.interfaces.request.UserRequest;
import br.com.hitbox.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/new")
    public ResponseEntity<User> createUserCompany(@RequestBody UserRequest request,
                                                  @AuthenticationPrincipal AuthenticatedUser user) {
        var result = userUseCase.create(mapper.toDomain(request), user);
        return null;
    }

    @GetMapping("load/users/by/role")
    public ResponseEntity<List<User>> loadUsersParameters(@AuthenticationPrincipal AuthenticatedUser user, @RequestParam UserRole userRole) {
        List<User> result = userUseCase.listUsersByRoleAndCompany(user, userRole);
        return ResponseEntity.ok(result);
    }


}
