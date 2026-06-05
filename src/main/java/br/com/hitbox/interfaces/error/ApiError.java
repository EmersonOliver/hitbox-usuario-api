package br.com.hitbox.interfaces.error;

import java.time.LocalDateTime;

public record ApiError(int status,
                       String message,
                       String path,
                       LocalDateTime timestamp) {
}
