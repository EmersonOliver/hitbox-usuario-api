package br.com.hitbox.interfaces;

import br.com.hitbox.core.usecase.ProfileUseCase;
import br.com.hitbox.interfaces.response.UserProfileResponse;
import br.com.hitbox.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileUseCase profileUseCase;

    @GetMapping
    public ResponseEntity<UserProfileResponse> profile(
            @AuthenticationPrincipal AuthenticatedUser user
    ) {

        return ResponseEntity.ok(
                profileUseCase.load(
                        user.getUserId()
                )
        );
    }


}
