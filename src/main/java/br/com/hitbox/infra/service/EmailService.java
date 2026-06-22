package br.com.hitbox.infra.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void send(
            String to,
            String subject,
            String html
    ) {

        try {

            MimeMessage message =
                    mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            true,
                            "UTF-8"
                    );

            helper.setTo(to);

            helper.setSubject(subject);

            helper.setText(
                    html,
                    true
            );

            mailSender.send(message);

        } catch (MessagingException | jakarta.mail.MessagingException e) {

            throw new RuntimeException(
                    "Erro ao enviar e-mail",
                    e
            );

        }
    }
}
