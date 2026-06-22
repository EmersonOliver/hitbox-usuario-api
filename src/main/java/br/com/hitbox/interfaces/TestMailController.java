package br.com.hitbox.interfaces;

import br.com.hitbox.infra.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test-mail")
public class TestMailController {

    private final EmailService emailService;

    @GetMapping
    public String send() {

        emailService.send(
                "emerson-developer@outlook.com",
                "Teste ERP Hitbox",
                "<h1>Email funcionando</h1>"
        );

        return "OK";
    }
}
