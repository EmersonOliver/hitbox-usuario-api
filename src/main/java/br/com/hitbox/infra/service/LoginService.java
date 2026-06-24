package br.com.hitbox.infra.service;

import br.com.hitbox.core.domain.CompanyMembership;
import br.com.hitbox.core.gateway.CompanyMembershipGateway;
import br.com.hitbox.infra.entity.UserEntity;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.interfaces.request.AuthRecord;
import br.com.hitbox.interfaces.response.CompanySelectionResponse;
import br.com.hitbox.interfaces.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final CompanyMembershipGateway membershipGateway;
    private final TokenService tokenService;


    public LoginResponse login(
            AuthRecord authRecord
    ) {
        try {
            var authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    authRecord.email(),
                                    authRecord.password()
                            )
                    );

            UserEntity user =
                    (UserEntity) authentication.getPrincipal();

            List<CompanyMembership> memberships =
                    membershipGateway.findByUserId(
                            user.getId()
                    );

            String temporaryToken =
                    tokenService.generateTemporaryToken(
                            user
                    );
            return LoginResponse.builder()
                    .email(user.getEmail())
                    .userName(user.getFullName())
                    .firstLogin(user.getFirstLogin())
                    .token(temporaryToken)
                    .companies(
                            memberships.stream()
                                    .map(m ->
                                            CompanySelectionResponse.builder()
                                                    .companyId(m.getCompanyId())
                                                    .companyName(m.getCompanyName())
                                                    .teamId(m.getTeamId())
                                                    .teamName(m.getTeamName())
                                                    .build())
                                    .toList()
                    ).build();
        } catch (
                InternalAuthenticationServiceException e
        ) {
            throw new HitboxException(
                    "Usuário não existe"
            );
        }
    }
}
