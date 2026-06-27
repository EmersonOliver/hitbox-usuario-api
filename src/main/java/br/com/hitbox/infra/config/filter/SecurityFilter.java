package br.com.hitbox.infra.config.filter;

import br.com.hitbox.infra.entity.UserEntity;
import br.com.hitbox.infra.exceptions.HitboxException;
import br.com.hitbox.infra.jpa.SpringDataUserRepository;
import br.com.hitbox.infra.service.TokenService;
import br.com.hitbox.security.AuthenticatedUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final SpringDataUserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    )
            throws ServletException, IOException {

        String token =
                recoverToken(request);


        if (token != null) {

            UUID userId =
                    tokenService.getUserId(
                            token
                    );

            UserEntity user =
                    userRepository.findById(
                                    userId
                            )
                            .orElseThrow(
                                    () ->
                                            new HitboxException(
                                                    "Usuário não encontrado"
                                            )
                            );

            AuthenticatedUser principal =
                    AuthenticatedUser.builder()
                            .userId(userId)
                            .companyId(
                                    tokenService.getCompanyId(token)
                            )
                            .teamId(
                                    tokenService.getTeamId(token)
                            )
                            .companyName(
                                    tokenService.getCompanyName(token)
                            )
                            .teamName(
                                    tokenService.getTeamName(token)
                            )
                            .email(
                                    tokenService.getEmail(token)
                            )
                            .fullName(
                                    tokenService.getFullName(token)
                            )
                            .userRole(
                                    tokenService.getUserRole(token)
                            )
                            .build();
            Collection<GrantedAuthority> authorities =
                    buildAuthorities(principal);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            principal,
                            null,
                            authorities
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            authentication
                    );
        }

        chain.doFilter(
                request,
                response
        );
    }
    public String recoverToken(HttpServletRequest httpServletRequest) {
        var authorization = httpServletRequest.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer ", "");
    }

    private Collection<GrantedAuthority> buildAuthorities(
            AuthenticatedUser userContext) {

        List<GrantedAuthority> authorities =
                new ArrayList<>();

        authorities.add(
                new SimpleGrantedAuthority(
                        "ROLE_" + userContext.getUserRole().name()
                )
        );

        userContext.getPermissions()
                .forEach(permission ->
                        authorities.add(
                                new SimpleGrantedAuthority(permission)
                        ));

        return authorities;
    }
}
