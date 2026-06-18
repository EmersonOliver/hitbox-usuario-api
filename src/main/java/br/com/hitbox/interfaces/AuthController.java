package br.com.hitbox.interfaces;

import br.com.hitbox.infra.service.LoginService;
import br.com.hitbox.interfaces.request.AuthRecord;
import br.com.hitbox.interfaces.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthRecord authRecord) {
        var response = loginService.login(authRecord);
        return ResponseEntity.ok(response);
    }

}
