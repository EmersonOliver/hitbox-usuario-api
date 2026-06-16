package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.usecase.TeamUseCase;
import br.com.hitbox.interfaces.request.TeamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamUseCase useCase;


    @PostMapping("create")
    public ResponseEntity<Team> createTeams(@RequestBody TeamRequest request) {
        var result = useCase.createTeam(request.getCompanyId(), request);
        return ResponseEntity.ok(result);
    }

}
