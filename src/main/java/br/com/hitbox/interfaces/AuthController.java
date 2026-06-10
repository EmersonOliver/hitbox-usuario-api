package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.User;
import br.com.hitbox.infra.entity.UserEntity;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.infra.service.TokenService;
import br.com.hitbox.interfaces.request.AuthRecord;
import br.com.hitbox.interfaces.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
        try{
            var userNamePassword = new UsernamePasswordAuthenticationToken(authRecord.email(), authRecord.password());
            var authenticate = authenticationManager.authenticate(userNamePassword);
            var token = tokenService.generateToken((UserEntity) authenticate.getPrincipal());

            var response = new LoginResponse();
            var usuario = (UserEntity) authenticate.getPrincipal();
            if (usuario == null) {
                throw new HitboxException("Usuario não encontrado!");
            }
            response.setEmail(usuario.getEmail());
            response.setToken(token);
            response.setFirstLogin(usuario.getFirstLogin());
            response.setCompanyId(usuario.getCompany() != null ? usuario.getCompany().getId() : null);
            response.setUser(usuario.getFullName());
            return ResponseEntity.ok(response);
        } catch (InternalAuthenticationServiceException e) {
            throw new HitboxException("Usuário não existe!");
        }

    }

}
