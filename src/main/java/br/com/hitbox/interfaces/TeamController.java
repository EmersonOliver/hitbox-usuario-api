package br.com.hitbox.interfaces;

import br.com.hitbox.core.domain.Team;
import br.com.hitbox.core.usecase.TeamUseCase;
import br.com.hitbox.infra.query.TeamQueryService;
import br.com.hitbox.interfaces.mapper.TeamResponseMapper;
import br.com.hitbox.interfaces.request.TeamRequest;
import br.com.hitbox.interfaces.response.TeamResponse;
import br.com.hitbox.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamUseCase useCase;
    private final TeamQueryService queryService;
    private final TeamResponseMapper responseMapper;


    @PostMapping("create")
    public ResponseEntity<Team> createTeams(@RequestBody TeamRequest request,
                                            @AuthenticationPrincipal AuthenticatedUser user) {
        var result = useCase.createTeam(user.getCompanyId(), request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("list/all/teams")
    public ResponseEntity<Page<TeamResponse>> listAllTeams(Pageable pageable, @AuthenticationPrincipal AuthenticatedUser user) {
        var result = queryService.listAllTeams(pageable, user.getCompanyId()).map(responseMapper::toResponse);
        return ResponseEntity.ok(result);
    }

    @GetMapping("findByTeamId/{teamId}")
    public ResponseEntity<TeamResponse> findTeamById(@PathVariable UUID teamId) {
        var response = queryService.findById(teamId);
        return ResponseEntity.ok(responseMapper.toResponse(response));
    }

}
