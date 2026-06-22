package br.com.hitbox.infra.service;

import br.com.hitbox.core.gateway.MailGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailGatewayImpl implements MailGateway {

    private final EmailService emailService;

    @Override
    public void sendInvitation(String email, String invitationLink) {
        String subject =
                "Você foi convidado para participar da empresa";

        String html =
                """
                <html>
                    <body>
                        <h2>Convite para acesso ao ERP</h2>
                        
                        <p>
                            Você foi convidado para participar da plataforma.
                        </p>
                        
                        <p>
                            Clique no link abaixo para concluir seu cadastro:
                        </p>
                        
                        <p>
                            <a href="%s">
                                Concluir cadastro
                            </a>
                        </p>
                        
                        <p>
                            Caso não tenha solicitado este acesso,
                            ignore este e-mail.
                        </p>
                    </body>
                </html>
                """
                        .formatted(invitationLink);

        emailService.send(
                email,
                subject,
                html
        );
    }
}
