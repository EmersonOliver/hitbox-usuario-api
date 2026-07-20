package br.com.hitbox.infra.config.interceptors;

import br.com.hitbox.infra.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtChannelInterceptor implements ChannelInterceptor {

    private final TokenService tokenService;

    @Override
    public Message<?> preSend(
            Message<?> message,
            MessageChannel channel
    ) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(
                accessor.getCommand()
        )) {

            String authorization =
                    accessor.getFirstNativeHeader(
                            "Authorization"
                    );

            if (authorization == null) {
                throw new RuntimeException(
                        "Token não informado"
                );
            }

            String token =
                    authorization.replace(
                            "Bearer ",
                            ""
                    );

            var decoded =
                    tokenService.getUserId(token);

            accessor.getSessionAttributes()
                    .put(
                            "userId",
                            decoded
                    );
        }

        return message;
    }

}
