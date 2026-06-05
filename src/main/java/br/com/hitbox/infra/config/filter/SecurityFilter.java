package br.com.hitbox.infra.config.filter;

import br.com.hitbox.infra.jpa.SpringDataUserRepository;
import br.com.hitbox.infra.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final SpringDataUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        var token = this.recoverToken(request);
        if (token != null) {
            var subject = tokenService.validateToken(token);
            var userId = UUID.fromString(subject);
            UserDetails userDetails = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, userId, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);

    }
    public String recoverToken(HttpServletRequest httpServletRequest) {
        var authorization = httpServletRequest.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer ", "");
    }
}
