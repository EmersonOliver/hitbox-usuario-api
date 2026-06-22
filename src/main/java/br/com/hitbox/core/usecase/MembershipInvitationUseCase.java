package br.com.hitbox.core.usecase;

import br.com.hitbox.core.gateway.CompanyMembershipRequestGateway;
import br.com.hitbox.core.gateway.MailGateway;
import br.com.hitbox.infra.enums.RequestStatus;
import br.com.hitbox.interfaces.request.CompanyMembershipRequest;
import br.com.hitbox.interfaces.request.InviteMemberRequest;
import br.com.hitbox.interfaces.response.InvitationDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MembershipInvitationUseCase {

    private final CompanyMembershipRequestGateway gateway;
    private final MailGateway mailGateway;

    public void invite(
            InviteMemberRequest request
    ) {

        String token =
                UUID.randomUUID().toString();

        CompanyMembershipRequest invitation =
                CompanyMembershipRequest.builder()
                        .companyId(request.companyId())
                        .teamId(request.teamId())
                        .email(request.email())
                        .role(request.role())
                        .invitationToken(token)
                        .status(RequestStatus.PENDING)
                        .requestedAt(LocalDateTime.now())
                        .expiresAt(
                                LocalDateTime.now().plusDays(7)
                        )
                        .build();

        invitation =  gateway.save(invitation);

        mailGateway.sendInvitation(
                request.email(),
               "http://localhost:4200/invite/"+ invitation.getInvitationToken()
        );
    }

    public InvitationDetailsResponse findInvitation(String token) {
        return null;
    }
}
