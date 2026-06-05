package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.infra.entity.UserEntity;
import br.com.hitbox.infra.service.TokenService;
import br.com.hitbox.interfaces.request.AuthRecord;
import br.com.hitbox.interfaces.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRecord authRecord) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(authRecord.email(), authRecord.password());
        var authenticate = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((UserEntity) authenticate.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token, ((UserEntity) authenticate.getPrincipal()).getName(), authRecord.email()));
    }

}
