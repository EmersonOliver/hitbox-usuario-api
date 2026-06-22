package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.CompleteRegistrationUseCase;
import br.com.hitbox.core.usecase.MembershipInvitationUseCase;
import br.com.hitbox.interfaces.request.CompleteRegistrationRequest;
import br.com.hitbox.interfaces.request.InviteMemberRequest;
import br.com.hitbox.interfaces.response.CompleteRegistrationResponse;
import br.com.hitbox.interfaces.response.InvitationDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("membership")
@RestController
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipInvitationUseCase invitationUseCase;
    private final CompleteRegistrationUseCase completeRegistrationUseCase;

    @PostMapping("/invite")
    public ResponseEntity<Void> invite(
            @RequestBody InviteMemberRequest request
    ) {

        invitationUseCase.invite(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/invitation/{token}")
    public ResponseEntity<InvitationDetailsResponse> invitation(
            @PathVariable String token
    ) {

        return ResponseEntity.ok(
                invitationUseCase.findInvitation(token)
        );
    }

    @PostMapping("/complete-registration")
    public ResponseEntity<CompleteRegistrationResponse> completeRegistration(
            @RequestBody CompleteRegistrationRequest request
    ) {

        return ResponseEntity.ok(
                completeRegistrationUseCase.complete(request)
        );
    }
}
