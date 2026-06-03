package br.com.hitbox.infra.query;

import br.com.hitbox.core.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {


    public Page<User> findAllUsers(Pageable pageable) {
        return null;
    }

    public Page<User> findByFilterText(Pageable pageable, String text) {
        return null;
    }

}
