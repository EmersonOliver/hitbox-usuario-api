package br.com.hitbox.core.gateway;

public interface MailGateway {
    void sendInvitation(
            String email,
            String invitationLink
    );
}
